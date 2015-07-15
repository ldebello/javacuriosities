package ar.com.javacuriosities.concurrency.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/*
 * La clase Semaphore permite definir un semáforo para el acceso a un recurso compartido. 
 * Debemos indicarle al constructor cuantos permits podrán ser usados al mismo tiempo.
 *  
 * Semaphore(10) = Crea diez tickets disponibles
 */
public class Main {

	public static void main(String args[]) {
		// Creamos un semáforo con 10 tickets
		Semaphore semaphore = new Semaphore(10);

		// Creamos un set de Threads que son ejecutados
		for (int i = 1; i <= 5; i++) {
			new Reader("Reader-" + i, semaphore).start();
		}

		for (int i = 1; i < 4; i++) {
			new Writer("Writer-" + i, semaphore).start();
		}

		for (int i = 6; i <= 10; i++) {
			new Reader("Reader-" + i, semaphore).start();
		}
	}

	/*
	 * Clase que representa un Escritor el cual necesita la exclusividad sobre
	 * el recurso para escribir
	 */
	private static final class Writer extends Thread {

		private Semaphore semaphore;

		public Writer(String name, Semaphore semaphore) {
			super(name);
			this.semaphore = semaphore;
		}

		@Override
		public void run() {
			try {
				System.out.println(getName() + ": Trying to write");

				/*
				 * Solicita 10 tickets, si no los hay se queda esperando El
				 * Escritor espera de que no haya nadie leyendo para escribir
				 */
				semaphore.acquire(10);

				System.out.println(getName() + ": wrote something");

				// Dormimos un tiempo al azar
				TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 50));

				// Liberamos 10 tickets
				semaphore.release(10);
			} catch (InterruptedException e) {
				// Log and Handle exception
				e.printStackTrace();
			}
		}
	}

	/*
	 * Representa un Lector el cual solo necesita 1 ticket para leer del recurso
	 */
	private static final class Reader extends Thread {

		private Semaphore semaphore;

		public Reader(String name, Semaphore semaphore) {
			super(name);
			this.semaphore = semaphore;
		}

		@Override
		public void run() {
			try {
				System.out.println(getName() + ": Trying to read");

				// Solicitamos un ticket
				semaphore.acquire();

				System.out.println(getName() + ": Read something");

				// Dormimos un tiempo al azar
				TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 50));

				// Liberamos un ticket
				semaphore.release();
			} catch (InterruptedException e) {
				// Log and Handle exception
				e.printStackTrace();
			}
		}
	}
}