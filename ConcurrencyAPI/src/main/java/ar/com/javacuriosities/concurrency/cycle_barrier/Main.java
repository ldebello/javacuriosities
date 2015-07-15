package ar.com.javacuriosities.concurrency.cycle_barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/*
 * CyclicBarrier puede ser usado cuando queramos ejecutar un proceso al
 * finalizar X procesos. Se tendrá que definir el número de procesos a tratar
 * antes de lanzar este proceso cíclico.
 */
public class Main {

    public static void main(String[] args) {
        /*
         * Se lanzará el proceso cíclico cada vez que CyclicBarrier alcanze la barrera, en este caso 
         * eso pasaría al tener 2 parties(threads).
         * Para añadir parties(threads) hay que llamar a await().
         */
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new CyclicProcess());

        new ThreadCyclicBarrier("Thread-1", cyclicBarrier).start();
        new ThreadCyclicBarrier("Thread-2", cyclicBarrier).start();
        new ThreadCyclicBarrier("Thread-3", cyclicBarrier).start();
        new ThreadCyclicBarrier("Thread-4", cyclicBarrier).start();
    }
    
    /*
     * Thread que simula un proceso que el sistema va a ejecutar de manera cíclica cuando el contador llegue a la cantidad indicada
     */
    private static final class CyclicProcess extends Thread {

        @Override
        public void run() {
            System.out.println("Checking system data...");
            /* Operaciones del sistema: 
             * comprobar memoria libre, estado del procesador, 
             * estado de las aplicaciones externas, etc. 
             */
            System.out.println("System OK");
        }
    }
    
    /*
     * Thread que recibe el CycleBarrier y cuando se ejecuta incrementa
     * sus parties
     */
    private static final class ThreadCyclicBarrier extends Thread {

        private CyclicBarrier cyclicBarrier;

        public ThreadCyclicBarrier(String name, CyclicBarrier cyclicBarrier) {
            super(name);
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("Checking:" + getName());
            try {
                /*
                 * Añadimos parties(threads) al CyclicBarrier así cuando se alcancen todos los parties(threads)  que definimos en el constructor se lanzará el hilo cíclico.  
                 */
            	cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
            	// Log and Handle exception
				e.printStackTrace();
            }
        }
    }
}
