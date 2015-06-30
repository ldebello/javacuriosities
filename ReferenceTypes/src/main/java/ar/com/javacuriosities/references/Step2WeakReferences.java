package ar.com.javacuriosities.references;

import ar.com.javacuriosities.references.util.Foo;

import java.lang.ref.WeakReference;

/*
 * Las WeakReference no cuentan como una referencia Strong por lo cual
 * el GC puede liberar la memoria cuando no hay ninguna referencia Strong.
 * El método get() devuelve NULL luego de que no haya mas referencias del tipo Strong,
 * aunque este método devuelva NULL esto no quiere decir que se haya limpiado de la
 * memoria porque aun falta ejecutar el método finalize, el cual podría revivir la instancia.
 * 
 * Las WeakReference no deben ser usadas para manejos de Cache ya que son liberadas antes que las SoftReference
 */
public class Step2WeakReferences {
    public static void main(String[] args) {
    	
        // Creamos una referencia a un objeto Foo del tipo Strong Reference
        Foo foo = new Foo();  

        System.out.println("Instance: " + foo);

        /*
         * Creamos un Weak Reference al objeto, esto funciona como un envoltorio
         * sobre la referencia
         */
        WeakReference<Foo> weakReference = new WeakReference<Foo>(foo);
        
        // Hacemos al objeto elegible por el GC (La referencia interna del Weak no se tiene en cuenta)
        foo = null;     
        
        // Obtenemos un Strong reference, ahora no es elegible por el GC
        Foo strongReference = weakReference.get();  

        System.out.println("Instance: " + strongReference);

        // Volvemos a hacerla elegible por el GC
        strongReference = null;

        // Sugerimos al GC correr
        System.gc();    

        // Esto deberla ser NULL si el GC fue ejecutado
        System.out.println("Instance: " + weakReference.get());
    }
}
