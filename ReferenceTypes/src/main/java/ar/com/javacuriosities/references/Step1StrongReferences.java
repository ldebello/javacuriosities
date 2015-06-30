package ar.com.javacuriosities.references;

import ar.com.javacuriosities.references.util.Foo;

/*
 * Las referencias del tipo Strong son las que usamos siempre y la regla
 * para el GC es que solo libera el objeto cuando no haya Strong referencias al objeto
 */
public class Step1StrongReferences {

	@SuppressWarnings("unused")
    public static void main(String[] args) {
        try {
            // Creamos una referencia a un objeto Foo del tipo Strong Reference
			Foo foo = new Foo();

            System.out.println("Before being eligible for GC");
            
            // Sugerimos al GC correr, aquí el objeto aun es referenciado
            System.gc();
                        
            Thread.sleep(2000);

            // Hacemos al objeto elegible por el GC
            foo = null;
            
            System.out.println("After being eligible for GC");

            // Volvemos a sugerir la limpieza
            System.gc();            
            
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        	// Log and Handle exception
        	e.printStackTrace();
        }
    }
}