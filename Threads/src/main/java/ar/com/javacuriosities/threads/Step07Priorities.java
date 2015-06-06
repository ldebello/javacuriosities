package ar.com.javacuriosities.threads;

import java.util.ArrayList;
import java.util.List;

public class Step07Priorities {

	public static void main(String[] args) {
		/*
		 * Iniciamos la cantidad maxima de thread que se pueden ejecutar en
		 * paralelo, mas dos adicionales para lograr que cuando se haga el
		 * yield() existan hilos a los cuales ceder el contexto
		 */
		int availableProcessors = Runtime.getRuntime().availableProcessors() + 2;

		// Creando threads
		List<Thread> threads = new ArrayList<>();
		for (int i = 0; i <= availableProcessors; i++) {
			Thread task = new Runner();
			task.setName("Runner-" + i);
			threads.add(task);
		}

		/*
		 * Asignamos la mínima prioridad a todos, y dejando el Runner-0 con maxima
		 * prioridad. Es importante recordar que los thread se crean con la
		 * misma prioridad que tiene el thread que los creo por defecto el
		 * Thread Main tiene prioridad Normal (5)
		 */
		for (Thread thread : threads) {
			thread.setPriority(Thread.MIN_PRIORITY);
		}

		threads.get(0).setPriority(Thread.MAX_PRIORITY);

		// Imprimimos la prioridad de cada Thread
		System.out.println("Priorities");
		System.out.println("Current Thread (Main thread): "
				+ Thread.currentThread().getPriority());
		for (Thread thread : threads) {
			System.out.println(thread.getName() + ": " + thread.getPriority());
		}

		/*
		 * Si intentamos poner una prioridad mayor a 10 o menor a 1 obtendremos
		 * una exception del tipo "java.lang.IllegalArgumentException"
		 */
		// threads.get(0).setPriority(999);

		// Start threads
		for (Thread thread : threads) {
			thread.start();
		}
	}

	public static final class Runner extends Thread {
		@Override
		public void run() {
			for (int distance = 1; distance <= 1000; distance++) {
				if (distance % 100 == 0) {
					System.out
							.println(getName() + " is at " + distance + " km");
				}
				if (Thread.MIN_PRIORITY == getPriority()) {
					Thread.yield();
				}
			}
			System.out.println(getName() + " arrives");
		}
	}
}
