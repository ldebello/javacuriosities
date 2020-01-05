package ar.com.javacuriosities.concurrency.fork_join_managedblock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Supplier;

/**
 * El framework fork/join pool ofrece una forma de indicar que la tarea es bloqueante para poder incrementar el pool antes de ejecutar
 * esta tarea y asi mantener el nivel de paralelismo alto, para esto debemos invocar el método estático "managedBlock" de "ForkJoinPool" y
 * proveer una implementación de "ManagedBlocker" el cual no pide la operación bloqueante y un boolean que indica si esta ya termino.
 *
 * En el siguiente ejemplo comparamos el tiempo usando managed blocked y sin usarlo, simulando una operación bloqueante de un valor minim de 200ms y maximo de 300ms.
 */
public class Main {

    public static void main(String[] args) {
        List<String> htmlPages = new ArrayList<>();

        // Generamos unas urls de donde se bajaría el HTML
        for (int i = 0; i < 100; i++) {
            htmlPages.add("http://javacuriosities.blogspot.com/" + i);
        }

        long blockingTotalTime = execute(htmlPages, Main::blockingExecute);
        long managedTotalTime = execute(htmlPages, Main::managedExecute);

        System.out.println("Blocking Total Time: " + blockingTotalTime);
        System.out.println("Managed Block Total Time: " + managedTotalTime);
    }

    private static long execute(List<String> htmlPages, Runnable logic) {
        long start = System.currentTimeMillis();

        // Es importante estar al tanto que todas las operaciones de parallel streams son ejecutadas en el fork/join pool common que utiliza tantos threads como numero de available processors.
        htmlPages.stream().parallel().forEach(htmlPage -> {
            System.out.println(Thread.currentThread().getName() + "- Start - Downloading HTML Page for: " + htmlPage);
            logic.run();
            System.out.println(Thread.currentThread().getName() + "- Finish - Downloading HTML Page for: " + htmlPage);
        });
        return System.currentTimeMillis() - start;
    }

    private static void blockingExecute() {
        Random r = new Random();
        try {
            // Simulamos la operación de IO
            Thread.sleep(r.nextInt((300 - 200) + 1) + 200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void managedExecute() {
        Random r = new Random();
        // En lugar de ejecutar la lógica directamente lo ejecutamos como un managedBlock
        new BlockingSupplier<Void>(() -> {
            try {
                Thread.sleep(r.nextInt((300 - 200) + 1) + 200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }).get();
    }

    private static class BlockingSupplier<T> implements Supplier<T> {
        private static final Object NULL = new Object();
        final Supplier<? extends T> supplier;
        volatile T result = (T) NULL;

        BlockingSupplier(Supplier<? extends T> supplier) {
            this.supplier = supplier;
        }

        @Override
        public T get() {
            try {
                ForkJoinPool.managedBlock(new ForkJoinPool.ManagedBlocker() {
                    @Override
                    public boolean block() {
                        result = supplier.get();
                        return true;
                    }

                    @Override
                    public boolean isReleasable() {
                        return result != NULL;
                    }
                });
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            return result;
        }
    }
}
