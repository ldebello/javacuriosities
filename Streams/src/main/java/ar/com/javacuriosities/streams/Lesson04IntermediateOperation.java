package ar.com.javacuriosities.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/*
 * Llamamos operaciones intermedias a aquellas que retornan otro Stream, estas operaciones
 * no son ejecutadas hasta que no hay una operación final. Podemos definir multiples parallel or sequential
 * streams pero dado que las intermediate operations no se ejecutan el ultimo llamado prevalece.
 * 
 * Todas las operaciones intermedias retornan LazyStreams
 */
public class Lesson04IntermediateOperation {
	public static void main(String[] args) {
		List<Integer> numbers = new ArrayList<>();
		
		numbers.add(33);
		numbers.add(66);
		numbers.add(11);
		numbers.add(33);
		numbers.add(99);
		numbers.add(88);
		numbers.add(22);
		
		Stream<Integer> stream = numbers.stream();
		
		System.out.println("Applying distinct operation");
		Stream<Integer> distinct = stream.distinct();
		
		System.out.println("Applying filter operation");
		Stream<Integer> filter = distinct.filter(n -> n > 44);
		
		System.out.println("Applying map operation");
		Stream<Integer> map = filter.map(n -> n * 2);
		
		System.out.println("Applying flatMap operation");
		// La operación flatMap recibe una Function que debe retorna un Stream el cual luego es recorrido convertido en un solo Stream
		Stream<Integer> flatMap = map.flatMap(n -> Stream.concat(Stream.of(n), Stream.of(n)));
		
		System.out.println("Applying skip operation");
		// Esta operación retorna un Stream que hace skip del primer elemento
		Stream<Integer> skip = flatMap.skip(1);
		
		System.out.println("Applying limit operation");
		// Esta operación retorna un Stream que contiene los primeros 4 elementos
		Stream<Integer> limit = skip.limit(4);
		
		System.out.println("Applying unordered operation");
		// Esta operación marca el Stream como desordenado lo cual puede mejorar la performance de operaciones como distinct() y groupingBy()
		Stream<Integer> unordered = limit.unordered();
		
		System.out.println("Applying sorted operation");
		// Podemos ordenar el Stream por su orden natural o por medio de un Comparator
		Stream<Integer> sorted = unordered.sorted();
		
		System.out.println("Applying peek operation");
		// En cualquier momento podemos agregar esta operación llamada peek, la cual toma un Consumer y no espera resultado, esta operación es muy útil para debugging
		Stream<Integer> peek = sorted.peek(System.out::println);
		
		// Ninguna de estas operaciones es ejecutada hasta que no haya una operación final
		System.out.println("Sum: " + peek.mapToInt(n-> n).sum());
	}
}