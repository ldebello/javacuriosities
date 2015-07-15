
package ar.com.javacuriosities.concurrency.single_thread_executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * Executors.newSingleThreadExecutor(): Crea un Pool de un solo Thread 
 * que ira agarrando tareas de una cola y las ira ejecutando.
 */
public class Main {

    public static void main(String[] args) {
        // Creamos un pool single thread que va ejecutando las tareas desde una cola de tareas
        ExecutorService executor = Executors.newSingleThreadExecutor();
        
        // Un único thread ejecutara estas 50 tareas
        for (int i = 0; i < 50; i++) {
            Runnable worker = new Task(10000000L + i);
            executor.execute(worker);            
        }
        
        /*
         * Esto hará que el executor no acepte nuevas tareas y que finalice cuando  el thread haya acabado con las 50 tareas
         */
        executor.shutdown();
       
        while (!executor.isTerminated()) {
        }
        
        System.out.println("Finish all the processes");
    }
    
    /*
     * Task es una implementación de Runnable la cual recibe un parámetro y su 
     * responsabilidad es contar desde el numero 1 hasta el valor asignado y 
     * después informar el resultado en la consola.
     */
    private static final class Task implements Runnable {

        private static int index;
        private final long countUntil;
        
        public Task(long countUntil) {
            this.countUntil = countUntil;
        }

        @Override
        public void run() {
            long total = 0;
            for (long i = 1; i < countUntil; i++) {
                total += i;
            }
            index++;
            System.out.println("Task number:" + index + " -  Total: " + total);
        }
    }
}
