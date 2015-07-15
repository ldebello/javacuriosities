package ar.com.javacuriosities.concurrency.reentrant_lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * ReentrantLock es la implementación de la interfaz Lock en este caso 
 * nos brinda exclusion mutua entre varios threads 
 *  
 */
public class Main {

    public static void main(String[] args) {
        // Creamos el Lock de exclusion mutua
        Lock lock = new ReentrantLock();
        
        // Creamos un Pool de 3 hilos
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        // Creamos un recurso compartido
        SharedResource sharedResource = new SharedResource();
        
        // Creamos 3 tareas y le pasamos el recurso y el lock que sirve de monitor
        Runnable task1 = new Worker("Worker-1", lock, sharedResource);
        Runnable task2 = new Worker("Worker-2", lock, sharedResource);
        Runnable task3 = new Worker("Worker-3", lock, sharedResource);
        
        // Ejecutamos las tres tareas
        executor.execute(task1);
        executor.execute(task2);
        executor.execute(task3);
        
        // No aceptamos nuevas tareas
        executor.shutdown();
    }
    
    /*
     * Este Worker va a ir usando el recurso compartido pero antes de usarlo
     * va a hacer un lock en el monitor para asegurar que nadie mas va a usarlo
     * al mismo tiempo
     */
    private static final class Worker implements Runnable {

        private Lock lock;
        private String name;
        private SharedResource sharedResource;

        public Worker(String name, Lock lock, SharedResource sharedResource) {
            this.name = name;
            this.lock = lock;
            this.sharedResource = sharedResource;
        }

        @Override
        public void run() {
            System.out.println(name + ": Trying to get lock...");
            
            /* 
             * lock(): Adquiere el bloqueo sobre el monitor y si no puede 
             * pone el Thread actual a dormir hasta que se pueda
             */
            lock.lock();
            System.out.println(name + ": Lock acquired");
            
            // Usamos el recurso compartido pero estamos tranquilo que solo nosotros lo tenemos
            sharedResource.increment();
            
            System.out.println(name + " incrementa contador: " + sharedResource.getCounter());
            try {
            	TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
            	// Log and Handle exception
				e.printStackTrace();
            } finally {
                System.out.println(name + ": Release lock");
                // Liberamos el recurso
                lock.unlock();
            }
        }
    }
    
    private static final class SharedResource {
        private int counter = 0;

        public void increment() {
        	counter++;
        }
        public int getCounter(){
            return counter;
        }
    }
    
    
}
