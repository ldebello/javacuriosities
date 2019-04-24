package ar.com.javacuriosities.collections;

import ar.com.javacuriosities.collections.elements.MapKey;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapExamples {

    public static void main(String[] args) {
        Map<String, Integer> mapa1 = new HashMap<>();
        Map<String, Integer> mapa2 = new LinkedHashMap<>();
        Map<String, Integer> mapa3 = new TreeMap<>();

        Map<String, Integer> mapa4 = new Hashtable<>();

        // Le agregamos un par clave-valor al primer map (Se lo conoce como diccionario)
        mapa1.put("Indice 4", 58);
        mapa1.put("Indice 5", 26);
        mapa1.put("Indice 6", 49);
        // Los HashMap aceptan un key con valor NULL
        mapa1.put(null, 1);

        /*
         * Agregamos numeros al segundo diccionario (Fíjense que son los mismo
         * clave-valor que arriba y otro adicional)
         */
        mapa2.put("Indice 4", 58);
        mapa2.put("Indice 3", 26);
        mapa2.put("Indice 2", 49);
        mapa2.put("Indice 1", 10);

        /*
         * Agregamos numeros al tercer diccionario (Exactamente los mismo que al segundo)
         */
        mapa3.put("Indice 4", 58);
        mapa3.put("Indice 3", 26);
        mapa3.put("Indice 2", 49);
        mapa3.put("Indice 1", 10);

        /*
         * Agregamos numeros al cuarto diccionario (Exactamente los mismo que al primero)
         * Los Hashtable son sincronizados y no aceptan NULL como Key.
         */
        mapa4.put("Indice 4", 58);
        mapa4.put("Indice 5", 26);
        mapa4.put("Indice 6", 49);

        /*
         * Iteramos los tres diccionarios con el foreach,
         * Aca el Map antes de iterar le tenemos que pedir un Set
         * que esta compuesto por objetos Entry el cual
         * contiene objetos Entry que almacenan la Key y Value
         */
        // HashMap no mantiene el orden de insertado
        System.out.println("Iterando Map 1");
        for (Map.Entry<String, Integer> entry1 : mapa1.entrySet()) {
            System.out.print(entry1.getKey() + ":");
            System.out.println(entry1.getValue());
        }

        // LinkedHashMap mantiene el orden de insertado
        System.out.println("Iterando Map 2");
        for (Map.Entry<String, Integer> entry2 : mapa2.entrySet()) {
            System.out.print(entry2.getKey() + ":");
            System.out.println(entry2.getValue());
        }

        // TreeMap ordena en base a la Key
        System.out.println("Iterando Map 3");
        for (Map.Entry<String, Integer> entry3 : mapa3.entrySet()) {
            System.out.print(entry3.getKey() + ":");
            System.out.println(entry3.getValue());
        }

        // Hashtable no mantiene el orden de insertado
        System.out.println("Iterando Map 4");
        for (Map.Entry<String, Integer> entry4 : mapa4.entrySet()) {
            System.out.print(entry4.getKey() + ":");
            System.out.println(entry4.getValue());
        }


        /*
         * Los Map no tiene método iterator porque no extienden de collection
         */

        System.out.println("Iterando Map con duplicados 1");
        /*
         * Vamos a crear un conjunto nuevo con String (O cualquier tipo de datos
         * que nosotros queramos) y agregar dos veces el mismo valor
         */
        Map<String, Integer> mapWithRepeteadValues1 = new HashMap<>();

        mapWithRepeteadValues1.put("Hola Cosme", 1);
        mapWithRepeteadValues1.put("Hola Cosme", 7);

        /*
         * Itero el mapa y vemos que "Hola Cosme" se imprime una sola
         * vez, porque los Set no aceptan elementos repetidos
         */
        for (Map.Entry<String, Integer> entryWithRepeteadValues1 : mapWithRepeteadValues1.entrySet()) {
            System.out.print(entryWithRepeteadValues1.getKey() + ":");
            System.out.println(entryWithRepeteadValues1.getValue());
        }

        System.out.println("Iterando Map con duplicados 2");
        /*
         * Creamos otro Map donde vamos a meter un elemento que creamos nosotros
         */
        Map<MapKey, Integer> mapWithRepeteadValues2 = new HashMap<>();

        MapKey key1 = new MapKey("Hola Pedro");
        MapKey key2 = new MapKey("Hola Pedro");

        mapWithRepeteadValues2.put(key1, 10);
        mapWithRepeteadValues2.put(key2, 15);

        /*
         * Para probar esto hay que comentar el equals y hashCode en MapKey
         * Como se puede ver al Iterar muestra dos valores, aunque yo les dije que
         * el Map no acepta duplicados pareciera como que si, esto sucede porque para
         * saber que dos objetos son iguales el Map utiliza el método hashCode y equals, entonces
         * si nuestro objeto no lo tiene implementado para Java son objetos distintos
         */
        for (Map.Entry<MapKey, Integer> entryWithRepeteadValues2 : mapWithRepeteadValues2.entrySet()) {
            System.out.print(entryWithRepeteadValues2.getKey() + ":");
            System.out.println(entryWithRepeteadValues2.getValue());
        }
    }
}
