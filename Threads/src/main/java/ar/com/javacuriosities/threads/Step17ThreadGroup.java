package ar.com.javacuriosities.threads;

public class Step17ThreadGroup {

	public static void main(String[] args) {
		// Creamos un Thread Group
		ThreadGroup threadGroup = new ThreadGroup("Group-01");

		// Creamos los hilos y los asignamos al grupo que creamos antes
		Thread thread1 = new Thread(threadGroup, new Task(), "Thread-01");
		Thread thread2 = new Thread(threadGroup, new Task(), "Thread-02");
		Thread thread3 = new Thread(threadGroup, new Task(), "Thread-03");

		/*
		 * Empezamos a iniciar cada hilo y vemos que el Thread Group brinda un estimado de la cantidad de hilos activos
		 */
		thread1.start();
		System.out.println("There are " + threadGroup.activeCount() + " thread running");

		thread2.start();
		System.out.println("There are " + threadGroup.activeCount() + " thread running");

		thread3.start();
		System.out.println("There are " + threadGroup.activeCount() + " thread running");
	}

	private static final class Task implements Runnable {
		@Override
		public void run() {
			System.out.println("Running task");
		}
	}
}