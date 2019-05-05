package ar.com.javacuriosities.threads;

/*
 * Existen varios tipos de locks, antes estuvimos usando un mutex y ahora usaremos
 * un spinlock, donde la diferencia radica en que este continua evaluando el lock (Por medio
 * de un busy waiting), y el mutex pone los threads a esperar. Esta técnica nos es util
 * cuando sabemos que la espera por el lock sera muy poca, ya que el thread se queda evaluando
 * el lock, por lo cual consume recursos del CPU pero la idea es que esos consumos serán menores
 * que ejecutar un context switch.
 */
public class Lesson19Spinlock {

	private static volatile boolean isRunning = true;

	public static void main(String[] args) {
		Thread thread = new Thread(new Task());
		thread.start();

		System.out.println("Waiting task to finish");

		// Spinlock using a busy waiting
		while(isRunning) {
		}

		System.out.println("Task finished");
	}

	private static final class Task implements Runnable {
		@Override
		public void run() {
			System.out.println("Executing task");
			isRunning = false;
		}
	}
}