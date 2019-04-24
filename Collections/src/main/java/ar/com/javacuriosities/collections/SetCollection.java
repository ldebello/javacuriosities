package ar.com.javacuriosities.collections;

import ar.com.javacuriosities.collections.elements.SetElement;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetCollection {

    public static void main(String[] args) {
        /*
         * Aquí definimos tres variables donde cada una usa una implementación
         * distinta de Set, el concepto de Set es como si fuera una bolsa de objetos
         * donde yo voy tirando ahi, al contrario que List los elementos no tienen
         * un orden sino que los tiro y después los recupero como van viniendo
         * a no ser que use una implementación que sea ordenada por ejemplo
         * TreeSet
         */
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new LinkedHashSet<>();
        Set<Integer> set3 = new TreeSet<>();

        // Le agregamos algunos numeros al primer conjunto
        set1.add(58);
        set1.add(26);
        set1.add(49);

        /*
         * Agregamos numeros al segundo conjunto (Fíjense que son los mismo
         * numeros que arriba y otro adicional)
         */
        set2.add(58);
        set2.add(26);
        set2.add(49);
        set2.add(10);

        /*
         * Agregamos numeros al tercer conjunto (Exactamente los mismo que al segundo)
         */
        set3.add(58);
        set3.add(26);
        set3.add(49);
        set3.add(10);

        /*
         * Iteramos los tres conjuntos con el foreach
         */
        // HashSet no mantiene el orden de insertado
        System.out.println("Iterando Set 1");
        for (Integer elementSet1 : set1) {
            System.out.println(elementSet1);
        }

        // LinkedHashSet mantiene el orden de insertado
        System.out.println("Iterando Set 2");
        for (Integer elementSet2 : set2) {
            System.out.println(elementSet2);
        }

        // TreeSet ordena en base al valor del elemento
        System.out.println("Iterando Set 3");
        for (Integer elementSet3 : set3) {
            System.out.println(elementSet3);
        }

        /*
         * Ahora vamos a iterar el primer Set usando el Iterator
         * Solo para ver como se usa y confirmar que también se puede hacer
         */
        Iterator<Integer> iteratorSet = set1.iterator();

        /*
         * Itero mientras haya elemento
         */
        System.out.println("Iterando Set 1 con Iterator");
        while (iteratorSet.hasNext()) {
            // Pido el siguiente elemento
            Integer element = iteratorSet.next();
            System.out.println(element);
        }

        System.out.println("Iterando Set con duplicados 1");
        /*
         * Vamos a crear un conjunto nuevo con String (O cualquier tipo de datos
         * que nosotros queramos) y agregar dos veces el mismo valor
         */
        Set<String> setWithRepeteadValues1 = new HashSet<>();

        setWithRepeteadValues1.add("Hola Cosme");
        setWithRepeteadValues1.add("Hola Cosme");

        /*
         * Itero el conjunto y vemos que "Hola Cosme" se imprime una sola
         * vez, porque los Set no aceptan elementos repetidos
         */
        for (String element : setWithRepeteadValues1) {
            System.out.println(element);
        }

        System.out.println("Iterando Set con duplicados 2");
        /*
         * Creamos otro Set donde vamos a meter un elemento que creamos nosotros
         */
        Set<SetElement> setWithRepeteadValues2 = new HashSet<>();

        SetElement elemento1 = new SetElement("Hola Cosme");
        SetElement elemento2 = new SetElement("Hola Cosme");

        setWithRepeteadValues2.add(elemento1);
        setWithRepeteadValues2.add(elemento2);

        /*
         * Para probar esto hay que comentar el equals y hashCode en SetElement
         * Como se puede ver al Iterar muestra dos valores, aunque yo les dije que
         * el Set no acepta duplicados pareciera como que si, esto sucede porque para
         * saber que dos objetos son iguales el Set utiliza el método hashcode y equals, entonces
         * si nuestro objeto no lo tiene implementado para Java son objetos distintos
         */
        for (SetElement setElement : setWithRepeteadValues2) {
            System.out.println(setElement);
        }
    }
}
