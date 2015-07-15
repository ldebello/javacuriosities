package ar.com.javacuriosities.concurrency.cached_thread_pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/*
 * Executors.newCachedThreadPool(): Crea un pool de threads conforme se vallan necesitando.
 * Si hubiera thread libres se reutilizan.
 * Si llegan 5 Threads se crearán 5 hilos para tratar las tareas, si a los 5 segundos
 * llegan 15 tareas mas, si hubiera hilos disponible se reutilizan y
 * llegan a faltar hilos se crean mas
 * Los hilos que estan inactivos mucho tiempo se terminan automáticamente, así que 
 * si llegaran nuevas tareas nuevos hilos seran creados.
 */
public class Main {

	private static final int NUMBER_OF_TASKS = 10;
	
    public static void main(String[] args) {
        // Creamos la cache de Threads
        ExecutorService executor = Executors.newCachedThreadPool();

        // Definimos una lista que contendrán los resultados futuros
        List<Future<Integer>> futures = new ArrayList<>();

        /*
         * Creamos las tareas y hacemos submit de cada una, cada submit devuelve un Future con el mismo 
         * generic type que el Callable, sobre le Future podremos ejecutar el método get() para obtener el resultado de callable cuando este haya terminado
         */
        for (int initialTaskId = 0; initialTaskId < NUMBER_OF_TASKS; initialTaskId++) {
        	futures.add(executor.submit(new MyCustomCallable(initialTaskId)));
        }

        // Recorremos las tareas y pedimos los valores cuando terminan
        for (Future<Integer> future : futures) {
        	try {
				System.out.println("Thread finishes: " + future.get());
			} catch (InterruptedException | ExecutionException e) {
				// Log and Handle exception
				e.printStackTrace();
			}
		}
        
        /*
         * El método shutdown le dice al executor que no reciba mas tarea
         * y que finalize las tareas pendientes que pueda llegar a tener
         */
        executor.shutdown();
    }
    
    /*
     * Clase que implementa de Callable.
     * Callable es una Interface similar a Runnable, pero a diferencia de Runnable, su único método call() 
     * devuelve un resultado (Del tipo que queramos) y permite lanzar una excepción.
     * El parámetro que devuelve el método se define por medio de Generic
     */
    private static final class MyCustomCallable implements Callable<Integer> {

        private int workerNumber;

        public MyCustomCallable(int workerNumber) {
            this.workerNumber = workerNumber;
        }

        @Override
        public Integer call() {
            for (int i = 0; i <= 100; i += 20) {
                System.out.println("Thread Number: " + workerNumber + ", percentage complete: " + i);
                try {
                	TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 100));
                } catch (InterruptedException e) {
                	// Log and Handle exception
    				e.printStackTrace();
                }
            }
            return workerNumber;
        }
    }

}
