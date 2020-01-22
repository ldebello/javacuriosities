package ar.com.javacuriosities.lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/*
 * Con la inclusion de Lambdas Expressions se agregaron
 * varios métodos en el API de Java.
 */
public class Lesson06NewMethods {

    public static void main(String[] args) {
        // Iterable interface: Se agrego el método forEach(Consumer<T> c)
        List<String> words = Arrays.asList("Welcome", "Lambdas", "to", "Java");

        words.forEach(word -> System.out.println(word));

        // Collection interface: Se agrego el método removeIf(Predicate<T> p)
        List<String> data = new ArrayList<String>(words);

        data.removeIf(element -> "Lambdas".equals(element));

        System.out.println(data);

        /*
         * List interface:
         * 	- Se agrego el método replaceAll(UnaryOperator<T> o)
         * 	- Se agrego el método sort(Comparator<T> c), este método reemplaza a Collections.sort(List<T> l, Comparator<T> c)
         */
        List<Integer> numbers = new ArrayList<>();
        numbers.add(99);
        numbers.add(11);
        numbers.add(33);

        data.replaceAll(element -> element.toUpperCase());

        System.out.println(data);

        numbers.sort((left, right) -> left.compareTo(right));

        System.out.println(numbers);

        /*
         * Logger class: Se agregaron variantes para poder recibir un Supplier
         * con esto logramos "Deferred Execution" lo cual quiere decir
         * que la lógica del Supplier no sera ejecutada hasta que sea necesario
         *
         */
        Logger logger = Logger.getLogger(Lesson06NewMethods.class.getName());

        // En este caso la concatenación de los String solo será realizada si el logger esta configurado en nivel INFO o alguno inferior
        logger.info(() -> "Hello" + " " + "World");

        /*
         * Map interface:
         * 	- Se agrego el método compute, computeIfAbsent, computeIfPresent, estos métodos sirven generalmente cuando queremos tomar un valor del Map hacer algo con el y guardar un nuevo valor para esa key
         * 	- Se agrego el método forEach
         * 	- Se agrego el método merge
         * 	- Se agrego el método getOrDefault
         * 	- Se agrego el método putIfAbsent, que antes solo estaba presente para ConcurrentHashMap
         * 	- Se agrego el método remove para un key-value especifico
         * 	- Se agrego el método replace para un key simple y también para un key-value especifico
         */
        Map<String, Integer> keyValuePairs = new HashMap<>();

        keyValuePairs.computeIfAbsent("FirstKey", (key) -> 99);
        keyValuePairs.computeIfAbsent("FirstKey", (key) -> 11);

        System.out.println(keyValuePairs);

        keyValuePairs.computeIfPresent("FirstKey", (key, value) -> 33);

        System.out.println(keyValuePairs);

        // Aca si la key existe podemos cambiar su valor sino estamos creando un nuevo entry en el Map
        keyValuePairs.compute("SecondKey", (key, value) -> 66);

        System.out.println(keyValuePairs);

        keyValuePairs.forEach((key, value) -> System.out.println(key + "=" + value));

        // Si la key no existe la función no es ejecutada
        keyValuePairs.merge("ThirdKey", 77, (currentValue, suggestValue) -> currentValue + suggestValue);

        System.out.println(keyValuePairs);

        // Si la key existe se ejecuta la función para hacer merge de los valores
        keyValuePairs.merge("ThirdKey", 33, (currentValue, suggestValue) -> currentValue + suggestValue);

        System.out.println(keyValuePairs);

        // El método getOrDefault busca la key o sino retorna el segundo parámetro
        System.out.println(keyValuePairs.getOrDefault("ForthKey", 100));

        keyValuePairs.remove("ThirdKey", 100);
        System.out.println(keyValuePairs);

        keyValuePairs.remove("ThirdKey", 110);
        System.out.println(keyValuePairs);

        keyValuePairs.replace("FirstKey", 99);
        System.out.println(keyValuePairs);
    }
}