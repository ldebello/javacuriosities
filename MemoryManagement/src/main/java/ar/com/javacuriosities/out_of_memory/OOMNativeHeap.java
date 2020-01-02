package ar.com.javacuriosities.out_of_memory;

import ar.com.javacuriosities.helper.MemoryUnit;

/*
 * Si incrementamos el tamaño del Heap vamos a poder crear menos Threads
 */
public class OOMNativeHeap {

    private static final int MAX_NB_THREADS = 5000;

    public static void main(String[] args) {
        int numberOfThreadCreated = 0;

        try {
            for (int i = 0; i < MAX_NB_THREADS; i++) {
                /*
                 * Creamos un nuevo hilo que se queda esperando por siempre
                 */
                IdleThread newJavaThread = new IdleThread();
                newJavaThread.setDaemon(true);

                // Ejecutamos el hilo
                newJavaThread.start();

                // Incrementamos el contador
                numberOfThreadCreated++;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            System.out.println("Hilos Creados: " + numberOfThreadCreated);
            System.out.println("Capacidad del Heap: "
                    + (MemoryUnit.BYTES.toMegaBytes(Runtime.getRuntime()
                    .maxMemory())) + " MB");
        }
    }
}
