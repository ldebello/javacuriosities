package ar.com.javacuriosities.concurrency.exchanger;

import java.util.concurrent.Exchanger;

/*
 * La clase Exchanger se encarga de sincronizar dos threads en un punto, cuando ambos threads llegan a ese punto intercambian un objeto. 
 * Exchanger puede ser visto como una forma bidireccional de un SynchronousQueue.
 * La clase Exchanger tiene el método exchange() el cual intercambia datos entre threads.
 * 
 * El problema de Producer/Consumer puede ser implementado con este mecanismo.
 */
public class Main {

	public static void main(String[] args) {
		Exchanger<String> exchanger = new Exchanger<>();
		
		Thread producer = new Thread(new Producer(exchanger));
		Thread consumer = new Thread(new Consumer(exchanger));

		producer.start();
		consumer.start();
	}

	private static final class Producer implements Runnable {
		private Exchanger<String> exchanger;

		public Producer(Exchanger<String> exchanger) {
			this.exchanger = exchanger;
		}

		public void run() {
			String[] messages = { "Hello", "World", "Bye" };
			for (String message : messages) {
				try {
					// Esperamos el intercambio con el consumidor;
					exchanger.exchange(message);
				} catch (InterruptedException e) {
					// Log and Handle exception
					e.printStackTrace();
				}
			}
		}
	}
	
	private static final class Consumer extends Thread {
		private Exchanger<String> exchanger;

		public Consumer(Exchanger<String> exchanger) {
			this.exchanger = exchanger;
		}

		public void run() {
			String message = "";
			while (!"Bye".equals(message)) {
				try {
					// Espera el intercambio con el productor
					message = exchanger.exchange(message);
					System.out.print(message + " ");
				} catch (InterruptedException e) {
					// Log and Handle exception
					e.printStackTrace();
				}
			}
		}
	}
}