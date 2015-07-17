package ar.com.javacuriosities.threads;

/*
 * Cuando nos encontramos con un deadlock podemos usar algunas utilidades 
 * para analizar el problema y resolverlo.
 * 
 * jvisualvm: Utilitario incluido en el JDK el cual nos permite varios analices entre ellos ver el estado de cada Thread
 * jmc: Utilitario incluido en el JDK el cual nos permite varios analices entre ellos ver el estado de cada Thread, es gratuito para desarrollo pero no para produccion
 * 
 * Independientemente de las herramientas que usemos lo ideal sera conseguir un Thread Dump lo cual es un volcado con las información de lo que estaba haciendo cada Thread.
 * Para esto podemos usar alguna de las herramientas solicitadas o jstack la cual viene incluida en el JDK y genera esta información
 */
public class Lesson14DeadLock {

	// Definimos dos objetos que vamos a usar como candados
	private final Object mutex1 = new Object();
	private final Object mutex2 = new Object();

	public static void main(String[] args) {
		// Creamos la clase que ejecuta un proceso que genera un Deadlock
		Lesson14DeadLock test = new Lesson14DeadLock();

		// Ejecutamos el método que genera el DeadLock
		test.deadLock();
	}

	private void deadLock() {
		Thread t1 = new Thread(new Locking12(this));
		Thread t2 = new Thread(new Locking21(this));
		t1.start();
		t2.start();
	}

	private void lock12() {
		System.out.println("Thread 1 is waiting for monitor 1");
		synchronized (mutex1) {
			System.out.println("Thread 1 has monitor 1");
			System.out.println("Thread 1 sleep");
			sleep();
			System.out.println("Thread 1 wake up");
			System.out.println("Thread 1 is waiting for monitor 2");
			synchronized (mutex2) {
				System.out.println("Thread 1 has monitor 2");
				System.out.println("Thread 1 sleep");
				sleep();
				System.out.println("Thread 1 wake up");
			}
		}
	}

	private void lock21() {
		System.out.println("Thread 2 is waiting for monitor 2");
		synchronized (mutex2) {
			System.out.println("Thread 2 has monitor 2");
			System.out.println("Thread 2 sleep");
			sleep();
			System.out.println("Thread 2 wake up");
			System.out.println("Thread 2 is waiting for monitor 1");
			synchronized (mutex1) {
				System.out.println("Thread 2 has monitor 1");
				System.out.println("Thread 2 sleep");
				sleep();
				System.out.println("Thread 2 wake up");
			}
		}
	}

	private void sleep() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}

	private static final class Locking12 implements Runnable {

		private Lesson14DeadLock deadLock;

		public Locking12(Lesson14DeadLock deadLock) {
			this.deadLock = deadLock;
		}

		@Override
		public void run() {
			deadLock.lock12();
		}
	}

	private static final class Locking21 implements Runnable {

		private Lesson14DeadLock deadLock;

		public Locking21(Lesson14DeadLock deadLock) {
			this.deadLock = deadLock;
		}

		@Override
		public void run() {
			deadLock.lock21();
		}
	}
}