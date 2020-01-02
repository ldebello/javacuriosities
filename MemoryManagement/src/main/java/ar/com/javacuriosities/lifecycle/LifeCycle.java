package ar.com.javacuriosities.lifecycle;

public class LifeCycle {
    public static void main(String[] args) {
        System.out.println("**** Ciclo de vida Baz ****");
        Baz baz = new Baz();

        /*
         * Si descomentamos las dos lineas de abajo veremos que se ejecuta el
         * método finalize, porque al asignar null a la variable baz estamos
         * eliminado toda referencia a ese objeto, entonces el Garbage Collector
         * puede liberar esa memoria
         */
        // baz = null;
        // System.gc();

        System.out.println();
        System.out.println("**** Ciclo de vida Foo ****");
        Foo foo = new Foo();

        System.out.println();
        System.out.println("**** Ciclo de vida FooBar ****");

        /*
         * Si la clase nunca fue inicializada y llamamos a un método estático,
         * primero se ejecutan el bloque estático de cada clase padre y el
         * bloque actual y luego se llama al método estático.
         */
        FooBar.metodoEstatico();

    }
}
