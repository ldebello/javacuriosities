package ar.com.javacuriosities.threads;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Step09SuspendAndResume {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		try {
			// Creamos objeto para ser usado como mutex
			Object mutex = new Object();

			Thread thread = new Task(mutex);
			thread.start();

			/*
			 * Si descomentamos esta línea se genera un deadlock porque el thread
			 * actual se detiene y le da tiempo al otro thread al adquirir un lock, el
			 * problema es que el suspend() frena el otro thread pero no libera los monitores usados
			 */
			// Thread.sleep(1);

			// Aquí suspendemos el thread anterior
			thread.suspend();
			
			System.out.println("Before locking mutex in Main Thread");
			
			synchronized (mutex) {
				System.out.println("After locking mutex in Main Thread");

				// Dormimos el hilo actual
				Thread.sleep(2000);

				// Ejecutamos el resume sobre el thread
				thread.resume();
			}
			System.out.println("After synchronized in Main Thread");

			/*
			 * Es importante notar que los métodos suspend() y resume() por lo cual
			 * no deberían usarse debido a que son Deadlock-prone
			 */
		} catch (InterruptedException e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}

	private static final class Task extends Thread {
		private Object mutex;

		public Task(Object mutex) {
			this.mutex = mutex;
		}

		@Override
		public void run() {
			System.out.println("Before locking mutex in Worker Thread");
			synchronized (mutex) {
				System.out.println("After locking mutex in Worker Thread");
				for (int i = 0; i < 100; i++) {
					System.out.println("Current number: " + i);
				}
			}
		}
	}
}
