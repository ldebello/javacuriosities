package ar.com.javacuriosities.lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
		 * que la logica del Supplier no sera ejecutada hasta que sea necesario
		 * 
		 */
		Logger logger = Logger.getLogger(Lesson06NewMethods.class.getName());
		
		// En este caso la concatenación de los String solo será realizada si el logger esta configurado en nivel INFO o alguno inferior
		logger.info(()->"Hello" + " " + "World");
	}
}
