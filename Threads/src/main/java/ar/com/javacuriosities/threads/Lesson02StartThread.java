package ar.com.javacuriosities.threads;

public class Lesson02StartThread {

	public static void main(String[] args) {
		Thread thread01 = new MyThread();

		/*
		 * A la hora de iniciar un Thread simplemente debemos invocar
		 * al método start(), el cual posteriormente va a invocar el método
		 * run() del Thread/Runnable
		 */
		thread01.start();
		
		/*
		 * Errores típicos:
		 * - Invocar al método run() en lugar del método start(), esto generara que el 
		 * código se ejecute en el hilo actual
		 * 
		 * - Invocar dos veces al método start() con la intención de volver a ejecutar
		 * el hilo, si hacemos esto nos encontraremos con "java.lang.IllegalThreadStateException"
		 */
	}
	
	private static class MyThread extends Thread {
		
		@Override
		public void run() {
			for (int i = 0; i < 100; i++) {
				System.out.println("Executing logic from Thread");
			}
		}
	}
}