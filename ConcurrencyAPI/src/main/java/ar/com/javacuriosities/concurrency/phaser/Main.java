package ar.com.javacuriosities.concurrency.phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/*
 * La clase Phaser (Incluida en Java 1.7) es similar a CyclicBarrier y CountdownLatch pero más flexible, las tareas o subprocesos se sincronizan en pasos o fases. 
 * Las partes registradas para sincronizar en un Phaser pueden variar con el tiempo, se puede cambiar de forma dinámica con los métodos register(), bulkRegister().
 * 
 * El método arriveAndDeregister() notifica al Phaser que una tarea ha terminado y que no participará en las futuras fases, se reduce el número de parties.
 * El método arriveAndAwaitAdvance() hace a una tarea esperar a que todos los participantes terminen.
 */
public class Main {

	public static void main(String[] args) {
		Phaser phaser = new Phaser();

		Thread thread1 = new Thread(new Task(phaser, 1000));
		Thread thread2 = new Thread(new Task(phaser, 3000));
		Thread thread3 = new Thread(new Task(phaser, 10000));

		thread1.start();
		thread2.start();
		thread3.start();
	}

	private static final class Task implements Runnable {

		private int sleep;
		private Phaser phaser;

		private Task(Phaser phaser, int sleep) {
			this.phaser = phaser;
			this.sleep = sleep;
		}

		public void run() {
			// Aca nos registramos como party
			phaser.register();
			System.out.println(Thread.currentThread().getName() + " Begin");

			try {
				TimeUnit.MILLISECONDS.sleep(sleep);
			} catch (InterruptedException e) {
				// Log and Handle exception
				e.printStackTrace();
			}

			// Aca informamos que llegamos y nos quedamos esperando por los demás parties
			phaser.arriveAndAwaitAdvance();
			System.out.println(Thread.currentThread().getName() + " Middle");
			try {
				TimeUnit.MILLISECONDS.sleep(sleep);
			} catch (InterruptedException e) {
				// Log and Handle exception
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " End");
		}
	}
}
