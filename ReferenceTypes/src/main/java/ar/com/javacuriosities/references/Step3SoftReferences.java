package ar.com.javacuriosities.references;

import ar.com.javacuriosities.references.util.Foo;

import java.lang.ref.SoftReference;

/*
 * Las SoftReference son similares a las Weak pero menos estrictas en cuando se debe liberar la memoria
 * dado que esperan a que la JVM este escasa de memoria.
 * 
 * Un uso muy común de este tipo de referencias es para la implementación de una Cache. 
 * Estas son liberadas usando un algoritmo LRU (Last Recent Used), además podemos pasarle parámetros a la JVM para modificar cierto comportamiento
 * 
 * -XX:SoftRelLRUPolicyMSPerMB: El Default Value es 1000 e indica cuanto tiempo vamos a retener las Soft Reference por cada MB libre en el Heap.
 * 
 * Las Soft Reference no tienen noción de su "peso":
 * - Memory Usage
 * - Computation time
 * - CPU Usage
 * 
 * Usando este tipo de referencia aseguramos que antes de un OOM (Out of Memory) todas las 
 * Soft Reference serán recolectadas
 */
public class Step3SoftReferences {

    public static void main(String[] args) {
        // Creamos una referencia a un objeto Foo del tipo Strong Reference
        Foo foo = new Foo();
        System.out.println("Instance: " + foo);

        // Creamos un Soft Reference al objeto
        SoftReference<Foo> softReference = new SoftReference<Foo>(foo);

        // Hacemos al objeto elegible por el GC
        foo = null;

        // Sugerimos al GC correr
        System.gc();

        // Seria NULL en caso de que la JVM este con escasa memoria
        System.out.println("Instance: " + softReference.get());
    }
}
