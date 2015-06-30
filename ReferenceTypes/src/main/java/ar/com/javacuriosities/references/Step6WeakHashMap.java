package ar.com.javacuriosities.references;

import java.util.Map;
import java.util.WeakHashMap;

import ar.com.javacuriosities.references.util.Foo;

/*
 * La clase java.util.WeakHashMap es una version especial HashMap, la cual
 * usa WeakReferences para las key y Strong para los values, por lo tanto
 * cuando un objeto no es objeto que es key no es mas referenciado este desaparece
 * del Map.
 * 
 * A veces podemos usar los Map para simular campos adicionales y una buena estrategia
 * puede ser usar este tipo de Map.
 * WeakHashMap no es concurrente.
 * La comparación de keys es por medio de equals
 */
public class Step6WeakHashMap {

    public static void main(String[] args) {
        try {
            // Creamos una referencia a un objeto Foo del tipo Strong Reference
            Foo foo = new Foo();
            
            // Creamos el Map y agregamos el objeto como Key
            Map<Foo, String> weakHashMap = new WeakHashMap<Foo, String>();
            weakHashMap.put(foo, "Testing WeakHashMap");

            System.out.println("Suggesting GC invocation");
            
            // Sugerimos al GC correr
            System.gc();

            Thread.sleep(1000);

            System.out.println("Is it size equal to 1? " + (weakHashMap.size() == 1));
            
            System.out.println("Assigning NULL");
            
            // Hacemos al objeto elegible por el GC 
            foo = null;
            
            System.out.println("Suggesting GC invocation");
            
            // Sugerimos al GC correr
            System.gc();
            
            Thread.sleep(1000);

            System.out.println("Is it size equal to 1? " + (weakHashMap.size() == 1));
        } catch (InterruptedException e) {
        	// Log and Handle exception
			e.printStackTrace();
        }
    }
}