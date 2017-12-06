package ar.com.javacuriosities.concurrency.fixed_thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * Executors.newFixedThreadPool(): Crea un Pool de threads con el numero indicado por parámetros, 
 * que siempre estarán preparados para procesar tareas. 
 * 
 * El pool maneja también una cola de tareas donde cada Thread coge una tarea de la cola 
 * y empieza a procesarla, cuando termina agarra otra. 
 */
public class Main {

	private static final int NUMBER_OF_THREADS = 10;

	public static void main(String[] args) {
		// Creamos un Pool de 10 hilos
		ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

		// Definimos 500 tareas a ser ejecutadas
		for (int i = 0; i < 500; i++) {
			Runnable worker = new Task(10000000L + i);
			executor.execute(worker);
		}

		/*
         * Esto hará que el executor no acepte nuevas tareas y que finalice cuando el thread haya acabado con las 50 tareas
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