package ar.com.javacuriosities.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/*
 * Los Streams pueden ser ejecutados de forma paralela o secuencial eso puede depender de la fuente o 
 * de si nosotros cambiamos su procesamiento, esto puede ser cambiado muchas veces pero solo la ultima
 * definición es la que se aplica.
 * 
 * La implementación de Parallel Stream usa el Framework Fork/Join, por defecto este usa un numero de Threads
 * igual a la cantidad de procesadores informados por el sistema operativo. Nosotros podemos cambiar esto
 * por medio de la siguiente system property
 * 
 * System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "32767");
 * 
 * Es importante notar y recordar que no siempre obtendremos mejor performance por medio de Streams paralelos, 
 * esto puede varias por distintos motivos, al final siempre debemos hacer profiling para confirmar nuestra decision
 * 
 * Para definir si usar parallel stream debemos tener en cuenta multiples aspectos:
 * 
 * - Estructura de datos:
 * ArrayList, HashSet, TreeSet -> Puede ser una buena opción
 * LinkedList -> No tan aconsejado
 * 
 * - Tipo de operación:
 * 
 * filter() y map() -> Son excelentes candidatos para operaciones paralelas
 * sorted() y distinct() -> No funcionan bien para operaciones paralelas
 * 
 * - Tamaño y costo:
 * 
 * N = Numero de elementos
 * Q = Costo de la operación para cada elemento
 * N x Q = Costo total
 * Mientras mas grande es N x Q es mejor para hacer esas operaciones de forma paralela
 */
public class Lesson10ParallelStreams {
	public static void main(String[] args) {
		List<String> programmingLanguages = new ArrayList<>();
		programmingLanguages.add("Scala");
		programmingLanguages.add("Java");
		programmingLanguages.add("PHP");
		programmingLanguages.add("JavaScript");
		
		// En base al Stream source podemos crear Stream secuenciales o paralelos
		Stream<String> sequentialStream = programmingLanguages.stream();
		Stream<String> parallelStream = programmingLanguages.parallelStream();
		
		// Si concatenamos un Stream secuencial con uno paralelo el resultado es un Stream paralelo
		Stream.concat(sequentialStream, parallelStream);
		
		// El Stream usara el ultimo modo asignado ya este sea parallel o sequential, debemos indicar la salvedad que el método findFirst siempre devolverá lo mismo sin importar el modo
		Stream<String> streamOfNames = Stream.of("Cosme Fulanito", "Pablo Marmol", "Pedro Picapiedra", "Super Sonico");
		Optional<String> firstNameUsingFindFirst = streamOfNames.sequential().parallel().findFirst();
		
		streamOfNames = Stream.of("Cosme Fulanito", "Pablo Marmol", "Pedro Picapiedra", "Super Sonico");
		Optional<String> firstNameUsingFindAny = streamOfNames.sequential().parallel().findAny();
		
		System.out.println("First Name Using Find First: " + firstNameUsingFindFirst);
		System.out.println("First Name Using Find Any: " + firstNameUsingFindAny);
	}
}