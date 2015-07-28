package ar.com.javacuriosities.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

/*
 * Nos referimos a Terminal Operation a aquellas que generan un resultado o un side-effect,
 * recién al ejecutar este tipo de operaciones se consume el pipeline, esto permite
 * 	- Lazy evaluation
 *	- Merged/Fused operations
 * 	- Elimination of redundant operations
 * 	- Parallel execution
 */
public class Lesson05TerminalOperation {
	public static void main(String[] args) {
		List<Integer> numbers = new ArrayList<>();

		numbers.add(33);
		numbers.add(66);
		numbers.add(11);
		numbers.add(33);
		numbers.add(44);
		numbers.add(77);
		numbers.add(99);
		numbers.add(88);
		numbers.add(22);
		
		// Tenemos varios métodos para hacer matching de elementos
		
		// findFirst(): Nos retorna el primer elemento por medio de un Optional en el caso que el Stream este vacio
		Optional<Integer> first = numbers.stream().filter(n -> n > 66).findFirst();
		
		first.ifPresent(n -> System.out.println("First: " + n));
		
		// findAny(): Funciona igual que findFirst() pero sirve para parallel streams
		Optional<Integer> findAny = numbers.stream().findAny();
		findAny.ifPresent(n -> System.out.println("Find Any: " + n));
		
		// allMatch(): Indica si todos los elementos del stream matchean el predicado
		boolean allMatch = numbers.stream().allMatch(n -> n > 66);
		System.out.println("All Match: " + allMatch);
		
		// anyMatch(): Indica si algún elemento del stream matchea el predicado
		boolean anyMatch = numbers.stream().anyMatch(n -> n > 66);
		System.out.println("Any Match: " + anyMatch);
		
		// noneMatch(): Indica si todos los elementos del stream NO matchean el predicado
		boolean noneMatch = numbers.stream().noneMatch(n -> n > 66);
		System.out.println("None Match: " + noneMatch);
		
		// collect(): Nos permite hacer una reducción mutable del Stream
		Integer collect = numbers.stream().collect(Collectors.summingInt(n -> n));
		System.out.println("Collect: " + collect);
		
		// toArray(): Nos permite obtener un array desde un Stream
		Object[] array = numbers.stream().toArray();
		System.out.println("Array: " + Arrays.toString(array));
		
		// count(): Retorna la cantidad de elementos en el Stream
		long count = numbers.stream().count();
		System.out.println("Count: " + count);
		
		// max(): Recibe un comparator y obtiene el máximo valor del Stream usando ese Comparator
		Optional<Integer> max = numbers.stream().max((n1, n2) -> n1.compareTo(n2));
		max.ifPresent(n -> System.out.println("Max: " + max));
		
		// min(): Recibe un comparator y obtiene el mínimo valor del Stream usando ese Comparator
		Optional<Integer> min = numbers.stream().min((n1, n2) -> n1.compareTo(n2));
		min.ifPresent(n -> System.out.println("Min: " + min));
		
		// forEach(): Nos permite recorrer el Stream
		System.out.println("For Each");
		numbers.parallelStream().forEach(System.out::println);
		
		// forEachOrdered() Nos garantiza el orden (Si es que existiera), esto es respetado incluso para los parallel Stream
		System.out.println("For Each Ordered");
		numbers.parallelStream().forEachOrdered(System.out::println);
		
		// reduce(): Nos permite hacer una reducción del Stream, o sea recorre el Stream y con un lambda BinaryOperator recibimos un accumulator y el valor del elemento actual
		Optional<Integer> reduce = numbers.stream().reduce((accumulator, value) -> accumulator + value);
		reduce.ifPresent(n -> System.out.println("Reduce: " + reduce));
		
		/* 
		 * Tenemos algunos métodos específicos para Stream primitivos, por ejemplo
		 * sum()
		 * average()
		 */
		OptionalDouble average = numbers.stream().mapToInt(n -> n).average();
		average.ifPresent(n -> System.out.println("Average: " + n));
		
		int sum = numbers.stream().mapToInt(n -> n).sum();
		System.out.println("Sum: " + sum);
		
		
	}
}
