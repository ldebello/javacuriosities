package ar.com.javacuriosities.concurrency.blocking_queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/*
 * Interface BlockingQueue
 * 
 * Sus métodos tienen 4 posibles formas de tratar las operaciones que no pueden ser atendidas inmediatamente 
 * pero si posiblemente en el futuro.
 * 
 * 1- Lanzar una excepción
 * 2- Devolver un valor null o false dependiendo de la operación
 * 3- Bloquear el thread actual indefinidamente hasta que se pueda realizar la operación.
 * 4- Bloquear durante un tiempo máximo dado.
 * 
 *            Throws exception - Special value  - Blocks  - Times out 
 *  Insert     add(e)             offer(e)         put(e)    offer(e, time, unit) 
 *  Remove     remove()           poll()           take()    poll(time, unit) 
 *  Examine    element()          peek()           -         - 
 * 
 * BlockingQueue no acepta valores nulos.
 * 
 * Puede tener una capacidad limitada. Si no se le indica nada su capacidad será Integer.MAX_VALUE.
 * 
 * BlockingQueue está diseñada para actuar como una cola "Productor/Consumidor"  (Insertar/Leer)  más que como una Collection y aunque dispone
 * de método como remove(x), no funcionan de forma muy eficiente y es mejor no usarlo mucho de esa manera. 
 * 
 * BlockingQueue es thread-safe, todos sus métodos de gestión de colas (no así lo típicos de una Collection) están preparados para la concurrencia. 
 * 
 * BlockingQueue no tiene un método de Close o shutdown para indicar que no se añadirán más items.
 * 
 * Una forma común de hacer esto sería que los Productor insertasen una clave de fin
 * que los Consumidor supieran interpretar. 
 * 
 * Implementaciones:
 * 	LinkedBlockingQueue —> Implementación con nodos conectados y limite opcional
 * 	ArrayBlockingQueue —> Implementación en la cual debemos especificar el limite
 * 	PriorityBlockingQueue —>  Implementación donde la prioridad esta dada por el resultado de comparación, los objetos deben ser comparables
 * 	DelayQueue —> Implementación en la cual los elementos están disponible después de cierto tiempo
 * 	SynchronousQueue —> Implementación similar rendezvous (Término de origen francés, Significado encuentro o cita), el tamaño es 0 y solo elemento esta disponible de un thread cuando otro lo remueve
 */
public class Main {

	public static void main(String[] args) {
		// Creamos una queue
		BlockingQueue<Integer> sharedQueue = new LinkedBlockingQueue<Integer>();

		// Creamos el Producer & Consumer
		Thread producer = new Thread(new Producer(sharedQueue));
		Thread consumer = new Thread(new Consumer(sharedQueue));

		// Ejecutamos los threads
		producer.start();
		consumer.start();
	}

	/*
	 * Representa al Productor que genera datos y los inserta en la queue
	 */
	private static final class Producer implements Runnable {

		private final BlockingQueue<Integer> sharedQueue;

		public Producer(BlockingQueue<Integer> sharedQueue) {
			this.sharedQueue = sharedQueue;
		}

		@Override
		public void run() {
			try {
				for (int i = 0; i < 10; i++) {
					System.out.println("Producer generating element number: " + i);
					sharedQueue.put(i);
				}
				sharedQueue.put(-1);
			} catch (InterruptedException e) {
				// Log and Handle exception
				e.printStackTrace();
			}
		}
	}

	/*
	 * Clase que representa un consumidor que consume desde la queue
	 */
	private static final class Consumer implements Runnable {

		private final BlockingQueue<Integer> sharedQueue;

		public Consumer(BlockingQueue<Integer> sharedQueue) {
			this.sharedQueue = sharedQueue;
		}

		@Override
		public void run() {
			int valor;
			boolean seguir = true;
			while (seguir) {
				try {
					valor = (Integer) sharedQueue.take();
					if (valor == -1) {
						seguir = false;
						continue;
					}

					System.out.println("Consumer picked: " + valor);
				} catch (InterruptedException e) {
					// Log and Handle exception
					e.printStackTrace();
				}
			}
		}
	}
}
