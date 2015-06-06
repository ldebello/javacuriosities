package ar.com.javacuriosities.threads;

public class Step13DaemonThread {

	public static void main(String[] args) {
		Thread thread01 = new Task();
		Thread thread02 = new Task();

		/*
		 * Para indicar que un thread es daemon debemos asignar "true" usando el
		 * método setDaemon(), esto le indica a la JVM que no es necesario
		 * esperar que este thread termina antes de destruir el proceso de la
		 * JVM
		 * 
		 * Esto debe asignarse antes de iniciar el thread, el Main Thread es
		 * User-Thread por eso la JVM siempre esperara porque su finalización
		 */
		thread01.setDaemon(true);
		thread02.setDaemon(true);

		// Start threads
		thread01.start();
		thread02.start();
	}

	private static class Task extends Thread {

		@Override
		public void run() {
			for (int i = 0; i < 100; i++) {
				System.out.println(getName() + " - Current number: " + i);
			}
		}
	}
}