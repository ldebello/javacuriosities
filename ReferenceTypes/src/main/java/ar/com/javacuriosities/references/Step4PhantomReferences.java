package ar.com.javacuriosities.references;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/*
 * Las Phantom References son una alternativa mas segura al método finalize, 
 * ya que el finalize permite resucitar el objeto, en cambio aquí seguimos la referencia pero
 * podemos ejecutar operaciones luego de que fue ejecutado el método finalize.
 * Estas dead reference son encoladas en la cola que le pasamos por parámetro
 * a la hora de construir este tipo de referencias.
 * 
 * El método get de este tipo de referencias siempre devuelve NULL porque la idea es hacer limpieza post-mortem
 * 
 * Es la única forma que tenemos de determinar cuando un objeto es removido de la memoria
 */
public class Step4PhantomReferences {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		try {
			/*
			 * Creamos una referencia a un objeto Foo del tipo Strong Reference
			 * Nota: Si el objeto que intentamos crear la Phantom Reference
			 * tiene el método finalize sobrescrito este no será agregado a la
			 * Queue. Esto aplica solo para Phantom Reference
			 */
			Object foo = new Object();

			ReferenceQueue<Object> queue = new ReferenceQueue<Object>();

			/*
			 * Creamos una Phantom Reference al objeto, siempre que usamos este
			 * tipo de referencia necesitamos una ReferenceQueue, la cual sirve
			 * para ser notificado
			 */
			PhantomReference<Object> phantomReference = new PhantomReference<Object>(
					foo, queue);

			// Hacemos al objeto elegible por el GC
			foo = null;

			new Thread(new Notification(queue)).start();

			// Esperamos 2s para iniciar el otro Hilo
			Thread.sleep(2000);

			System.out.println("Suggesting GC");

			// Sugerimos al GC correr
			System.gc();
		} catch (InterruptedException e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}

	private static final class Notification implements Runnable {

		private ReferenceQueue<Object> queue;

		public Notification(ReferenceQueue<Object> queue) {
			this.queue = queue;
		}

		@SuppressWarnings("all")
		@Override
		public void run() {
			try {
				System.out.println("Waiting for GC");

				/*
				 * Aquí se bloqueara hasta que la referencia sea recolectada,
				 * otra opción es el método poll pero este no es bloqueante,
				 * sino que devuelve NULL si no hay referencias en la cola
				 */
				PhantomReference<Object> phantomReference = (PhantomReference<Object>) queue.remove();

				System.out.println("Reference processed for GC");
			} catch (InterruptedException e) {
				// Log and Handle exception
				e.printStackTrace();
			}
		}

	}
}
