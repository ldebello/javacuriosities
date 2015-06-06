package ar.com.javacuriosities.threads;

import java.util.ArrayList;
import java.util.List;

class Step05YieldMethod {

	public static void main(String[] args) {
		/*
		 * Iniciamos la cantidad maxima de thread que se pueden
		 * ejecutar en paralelo, mas dos adicionales para lograr que cuando
		 * se haga el yield() existan hilos a los cuales ceder el contexto
		 */
		int availableProcessors = Runtime.getRuntime().availableProcessors() + 2;
		
		// Creando threads
		List<Thread> threads = new ArrayList<>();
		for (int i = 0; i <= availableProcessors; i++) {
			Thread task = new Task();
			task.setName("Task-" + i);
			threads.add(task);
		}

		// Start threads
		for (Thread thread : threads) {
			thread.start();
		}
	}

	private static class Task extends Thread {

		@Override
		public void run() {
			System.out.println("Start: " + getName());
			/*
			 * El método estático yield() es un hint para informar que estamos
			 * dispuesto a donar nuestro time slice para que otro thread se
			 * ejecute. 
			 * Si descomentamos esta línea veremos que hay mas switch entre los thread
			 */
			// Thread.yield();
			System.out.println("Finish: " + getName());
		}
	}
}