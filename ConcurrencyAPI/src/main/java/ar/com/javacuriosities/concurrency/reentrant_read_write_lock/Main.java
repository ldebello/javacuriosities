package ar.com.javacuriosities.concurrency.reentrant_read_write_lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

/*
 * La clase ReentrantReadWriteLock se usa cuando queremos tener distintos tipos
 * de locks para las operaciones de escritura y lectura, o sea puede haber  
 * multiples operaciones de lectura siempre y cuando no se este realizando
 * ninguna operación de escritura, y la escritura iniciara cuando nadie este leyendo 
 * el recurso
 */
public class Main {

	private static final int NUMBER_OF_THREADS = 40;

	public static void main(String[] args) {
		// Creamos un Lock de escritura y lectura
		ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

		// Creamos un Pool de 40 Threads
		ExecutorService executor = Executors
				.newFixedThreadPool(NUMBER_OF_THREADS);

		// Ejecutamos distintas tareas
		for (int i = 1; i <= 10; i++) {
			executor.execute(new Reader("Reader-" + i, lock.readLock()));
		}
		for (int i = 1; i <= 10; i++) {
			executor.execute(new Writer("Writer-" + i, lock.writeLock()));
		}
		for (int i = 1; i <= 10; i++) {
			executor.execute(new Reader("Reader-" + i, lock.readLock()));
		}

		// No aceptamos nuevas tareas
		executor.shutdown();
		
		while (!executor.isTerminated()) {
        }
	}

	/*
	 * Creamos un Thread que van a ejecutar operaciones de escritura Un solo
	 * escritor puede estar al mismo tiempo.
	 */
	private static final class Writer extends Thread {

		/*
		 * Usamos un WriteLock el cual tiene exclusion mutua para las escrituras
		 * de Write
		 */
		private WriteLock lock;

		public Writer(String name, WriteLock lock) {
			super(name);
			this.lock = lock;
		}

		@Override
		public void run() {
			try {
				System.out.println(getName() + ": Trying to write");

				/*
				 * Al pedir el lock() se va a comprobar que no haya nadie
				 * escribiendo y que tampoco haya ningún lector
				 */
				lock.lock();
				System.out.println(getName() + ": wrote something");

				TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 50));
			} catch (InterruptedException e) {
				// Log and Handle exception
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
	}

	/*
	 * Esta clase representa los lectores, estos puede haber ejecutar lecturas
	 * de forma concurrente pero no pueden leer si un escritor
	 */
	private static final class Reader extends Thread {

		/*
		 * El ReadLock nos deja leer hasta detectar un Writer
		 */
		private ReadLock lock;

		public Reader(String nombre, ReadLock lock) {
			super(nombre);
			this.lock = lock;
		}

		@Override
		public void run() {
			try {
				System.out.println(getName() + ": Trying to read");

				/*
				 * Obtenemos el lock() siempre y cuando no haya nadie escribiendo
				 */
				lock.lock();
				System.out.println(getName() + ": Read something");

				TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 50));
			} catch (InterruptedException e) {
				// Log and Handle exception
				e.printStackTrace();
			} finally {
				// Liberamos a esta lector
				lock.unlock();
			}
		}
	}
}