package ar.com.javacuriosities.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class ListCollection {

    public static void main(String[] args) {
        /*
         * Definimos tres variables que estan definidas del tipo
         * List pero cada una usa una implementación distinta,
         * dependiendo la implementación que usemos pueden variar cosas
         * como el orden, performance, y modos de iteración (Iterar = Recorrer)
         */
        List<Integer> listIntegers1 = new ArrayList<>();
        List<Integer> listIntegers2 = new LinkedList<>();

        /*
         * Lo esta entre los <> es el tipo de dato, como ven es necesario
         * ponerlo de los dos lados, en Java 1.7 ya no es necesario y se usa inferencia
         * de tipos o sea se da cuenta que pusimos de una lado y usa eso.
         *
         * Es importante mencionar que la implementación Vector tiene todos sus métodos
         * sincronizados
         */
        List<String> listIntegers3 = new Vector<>();

        // Le agregamos unos numeros a la primer lista
        listIntegers1.add(1);
        listIntegers1.add(2);
        listIntegers1.add(3);

        // Agregamos numeros a la segunda lista
        listIntegers2.add(4);
        listIntegers2.add(5);
        listIntegers2.add(6);
        listIntegers2.add(7);

        // Agregamos Strings a la tercer lista
        listIntegers3.add("Hola 1");
        listIntegers3.add("Hola 2");
        listIntegers3.add("Hola 3");
        listIntegers3.add("Hola 4");
        listIntegers3.add("Hola 5");

        System.out.println("Iterando Lista 1");
        /*
         * Todas las collections son iterables, o sea se pueden recorrer
         * y proveen un método que nos devuelve su iterador
         */
        Iterator<Integer> iterator1 = listIntegers1.iterator();

        /*
         * Hacemos un while que diga, mientras haya mas elementos
         * segui recorriendo, esta era la forma típica en Java 1.4
         */
        while (iterator1.hasNext()) {
            // El método next devuelve el siguiente elemento
            Integer element = iterator1.next();

            System.out.println(element);
        }

        System.out.println("Iterando Lista 2");
        /*
         * Esto es el foreach y apareció en Java 1.5 cualquier objeto que sea
         * Iterable o sea que implemente la interfaz iterable va a poder usar esta
         * forma
         */
        for (Integer elemento : listIntegers2) {
            System.out.println(elemento);
        }

        System.out.println("Revisando metodos comunes");
        /*
         * Si revisamos los método que ofrecen las variables listIntegers1, listIntegers2
         * listIntegers3, veremos que son los mismo porque estaba definidos como su interfaz
         */

        // Cada lista imprime su size
        System.out.println(listIntegers1.size());
        System.out.println(listIntegers2.size());
        System.out.println(listIntegers3.size());

        System.out.println("Haciendo casteo de elemento");
        /*
         * Aca casteamos la lista que estaba como tipo List a tipo LinkedList
         * esto nos va a dejar usar los métodos de LinkedList
         */
        LinkedList<Integer> linkedList = (LinkedList<Integer>) listIntegers2;

        /*
         * Si descomentan la linea de abajo van a ver que el método getLast() no aparece
         * como valido en la Interfaz List
         */
        //System.out.println(listIntegers2.getLast());

        /*
         * Vamos a imprimir el ultimo elemento de la segunda lista
         */
        System.out.println(linkedList.getLast());

        System.out.println("Iterando List con duplicados");
        /*
         * Vamos a crear una lista nueva con String (O cualquier tipo de datos
         * que nosotros queramos) y agregar dos veces el mismo valor
         */
        List<String> listWithRepeteadValues = new ArrayList<>();

        listWithRepeteadValues.add("Hola Cosme");

        if (listWithRepeteadValues.contains("Hola Cosme")) {
            System.out.println("Elemento ya existente agregando otra vez el mismo");
            listWithRepeteadValues.add("Hola Cosme");
        } else {
            System.out.println("El elemento no existe, agregandolo");
            listWithRepeteadValues.add("Hola Cosme");
        }


        /*
         * Itero la lista con elemento duplicados, y vemos que "Hola Cosme"
         * se imprime dos veces
         */
        for (String element : listWithRepeteadValues) {
            System.out.println(element);
        }
    }
}
