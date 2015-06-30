package ar.com.javacuriosities.references;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

import ar.com.javacuriosities.references.util.Foo;

/*
 * Las ReferenceQueue nos brinda un mecanismo de notificación
 * para poder saber cuando una referencia esta a punto de ser recolectada.
 * Las ReferenceQueue pueden ser suministradas como parámetros a cualquiera
 * de los tipos de referencias.
 */
public class Step5ReferenceQueues {

    private static Foo foo;

    public static void main(String[] args) {
        // Creamos una referencia a un objeto Foo del tipo Strong Reference
        foo = new Foo();

        /*
         * Creamos una ReferenceQueue en la cual registramos la referencia la cual será agregada 
         * a la lista luego que la referencia alcance su estado de "reachability"
         * 
         * Niveles de reachibility
         * - Strong
         * - Soft
         * - Weak
         * - Finalizer
         * - Phantom
         * - Unreachable
         */
        ReferenceQueue<Object> queue = new ReferenceQueue<Object>();

        /*
         * Creamos una WeakReference y la asignamos a la cola que creamos anteriormente
         * - Si usamos una WeakReference sabemos cuando este objeto no es referenciado, pero antes de ejecutar finalize
         * - Si usamos una PhantomReference el objeto esta por ser limpiado de la memoria luego de finalize
         */
        WeakReference<Object> weakReference = new WeakReference<Object>(foo, queue);

        // Iniciamos un Thread que se va a encargar de limpiar la referencia al objeto
        new Thread(new Task()).start();

        try {
            // Aquí sacamos la referencia de la cola, esto es bloqueante hasta que encuentra alguna
            Reference<?> reference = queue.remove();

            if (reference == weakReference) {
                System.out.println("The object is not more referenced");
            }

        } catch (InterruptedException e) {
        	// Log and Handle exception
			e.printStackTrace();
        }
    }
    
    private static final class Task implements Runnable {

		@Override
		public void run() {
			try {
                Thread.sleep(1000);
                System.out.println("Asigning object to NULL");
                
                // Hacemos al objeto elegible por el GC
                foo = null;
                
                System.out.println("Executing GC...");
                
                // Sugerimos al GC correr
                System.gc();
            } catch (Exception e) {
    			// Log and Handle exception
    			e.printStackTrace();
            }
		}
    }
}