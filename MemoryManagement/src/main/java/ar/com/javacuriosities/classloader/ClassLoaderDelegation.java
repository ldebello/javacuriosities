package ar.com.javacuriosities.classloader;

public class ClassLoaderDelegation {

    public static void main(String[] args) {
        /*
         * Al solicitar el ClassLoader de Foo vemos que fue levantado por el
         * AppClassLoader
         */
        ClassLoader applicationClassLoader = Foo.class.getClassLoader();

        /*
         * Los ClassLoader usan un sistema de delegación para ir solicitando la
         * carga de la clase al obtener nuestro AppClassLoader y solicitar su
         * padre nos encontramos con el siguiente en la jerarquía el
         * ExtClassLoader
         */
        ClassLoader extensionClassLoader = applicationClassLoader.getParent();

        /*
         * Al solicitar el padre de ExtClassLoader, estamos buscando el
         * bootstrap class loader en muchas implementación de la JVM este es
         * representado con NULL
         */
        ClassLoader bootstrapClassLoader = extensionClassLoader.getParent();

        System.out.println(applicationClassLoader);
        System.out.println(extensionClassLoader);
        System.out.println(bootstrapClassLoader);

    }
}
