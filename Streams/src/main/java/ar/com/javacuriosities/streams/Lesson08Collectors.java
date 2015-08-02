package ar.com.javacuriosities.streams;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * Cuando nos referimos a collectors, debemos pensar en una reducción mutable de un Stream, esto quiere
 * decir, que acumulamos elementos del input en un contenedor mutable (List, Map, String, etc). 
 * Dado que los String no son mutables en realidad estas operaciones generan nuevos Strings en general concatenando los mismos.
 * 
 * El método collect() es una terminal operation por lo cual termina el Stream, además veremos que tenemos la clase Collectors
 * la cual provee varios métodos de ayuda
 * 
 * En base a un Collector podemos crear composiciones, lo cual permite ejecutar un Collector sobre el resultado del anterior
 */
public class Lesson08Collectors {
	public static void main(String[] args) {
		// Podemos hacer un collect para dejar nuestros datos en una Collection
		Stream<String> streamOfNames = Stream.of("Cosme Fulanito", "Pablo Marmol", "Pedro Picapiedra");
		List<String> listOfName = streamOfNames.collect(Collectors.toList());
		System.out.println("List Collector: " + listOfName);
		
		streamOfNames = Stream.of("Cosme Fulanito", "Pablo Marmol", "Pedro Picapiedra");
		Set<String> setOfName = streamOfNames.collect(Collectors.toSet());
		System.out.println("Set Collector: " + setOfName);
		
		/* 
		 * También podemos pasar nuestro propio Collector, donde tenemos tres parámetros:
		 * 	- Supplier<R> supplier: Se encarga de proveer la instancia del contenedor que vamos a usar
		 * 	- BiConsumer<R, ? super T> accumulator: Se encarga de consumir el contenedor y el siguiente elemento
		 * 	- BiConsumer<R, R> combiner: Se encarga de combinar los resultado de los dos contenedores en caso de un merge
		 */
		streamOfNames = Stream.of("Cosme Fulanito", "Pablo Marmol", "Pedro Picapiedra");
		List<String> customCollector = streamOfNames.collect((Supplier<List<String>>) Lesson08Collectors::buildContainer, List::add, Lesson08Collectors::combineContainers);
		System.out.println("Custom Collector: " + customCollector);
		
		//Podemos hacer un collect a una estructura Map, para esto debemos proveer dos funciones una para el key y otra para el value
		streamOfNames = Stream.of("Cosme Fulanito", "Pablo Marmol", "Pedro Picapiedra");
		
		// Podemos usar Function.identity() para definir una lambda del estilo object -> object básicamente devuelve lo mismo 
		Map<String, Integer> mapOfNamesAndLengths = streamOfNames.collect(Collectors.toMap(Function.identity(), name-> name.length()));
		System.out.println("1- Map Collector: " + mapOfNamesAndLengths);
		
		// Tenemos algunas variantes para manejar casos donde las key estén duplicadas, en nuestro caso vamos a sumar las longitudes
		streamOfNames = Stream.of("Cosme Fulanito", "Pablo Marmol", "Pedro Picapiedra", "Cosme Fulanito");
		Map<String, Integer> mapOfDuplicateNamesAndLengths = streamOfNames.collect(Collectors.toMap(Function.identity(), name-> name.length(), (x, y)-> x + y));
		System.out.println("2- Map Collector: " + mapOfDuplicateNamesAndLengths);
		
		/* 
		 * Usando collectors podemos ejecutar operaciones de groupBy, para esto debemos proveer una función
		 * y el resultado es Map<K, List<V>>
		 */
		streamOfNames = Stream.of("Cosme Fulanito", "Pablo Marmol", "Pedro Picapiedra", "Super Sonico");
		Map<Integer, List<String>> groupingByLength = streamOfNames.collect(Collectors.groupingBy(String::length));
		System.out.println("Grouping by lenght: " + groupingByLength);
		
		// Podría ser el caso donde queremos aplicar un collector al resultado de cada grupo, esto se conoce como downstream collector
		streamOfNames = Stream.of("Cosme Fulanito", "Pablo Marmol", "Pedro Picapiedra", "Super Sonico");
		Map<Integer, Long> groupingByLengthAndCounting = streamOfNames.collect(Collectors.groupingBy(String::length, Collectors.counting()));
		System.out.println("Grouping by lenght and counting: " + groupingByLengthAndCounting);
		
		// Otra operación muy común es hacer joining
		streamOfNames = Stream.of("Cosme Fulanito", "Pablo Marmol", "Pedro Picapiedra", "Super Sonico");
		String names = streamOfNames.collect(Collectors.joining());
		System.out.println("Join: " + names);
		
		streamOfNames = Stream.of("Cosme Fulanito", "Pablo Marmol", "Pedro Picapiedra", "Super Sonico");
		names = streamOfNames.collect(Collectors.joining(","));
		System.out.println("Join with delimiter: " + names);
		
		streamOfNames = Stream.of("Cosme Fulanito", "Pablo Marmol", "Pedro Picapiedra", "Super Sonico");
		// El prefix y suffix son aplicados luego del join
		names = streamOfNames.collect(Collectors.joining(",", "Guys: ", " See you"));
		System.out.println("Join with delimiter, prefix and suffix: " + names);
		
		// Tenemos varios collectors del tipo numéricos, estos están disponibles tanto para Int, Long y Double
		streamOfNames = Stream.of("Cosme Fulanito", "Pablo Marmol", "Pedro Picapiedra", "Super Sonico");
		Double averageLength = streamOfNames.collect(Collectors.averagingInt(String::length));
		
		streamOfNames = Stream.of("Cosme Fulanito", "Pablo Marmol", "Pedro Picapiedra", "Super Sonico");
		Integer sumLength = streamOfNames.collect(Collectors.summingInt(String::length));
		
		streamOfNames = Stream.of("Cosme Fulanito", "Pablo Marmol", "Pedro Picapiedra", "Super Sonico");
		Optional<String> maxByLength = streamOfNames.collect(Collectors.maxBy((x, y) -> x.compareTo(y)));
		
		// La operación summarizingInt entrega el resultado de (count, sum, min, max, average) generados sobre la función aplicada
		streamOfNames = Stream.of("Cosme Fulanito", "Pablo Marmol", "Pedro Picapiedra", "Super Sonico");
		IntSummaryStatistics statistics = streamOfNames.collect(Collectors.summarizingInt(String::length));
		
		System.out.println("Average Lenght: " + averageLength);
		System.out.println("Sum Lenght: " + sumLength);
		System.out.println("Max Lenght: " + maxByLength.get());
		System.out.println("Statistics Lenght: " + statistics);
		
		/* 
		 * Se dice que varios métodos de Collectors tiene versiones downstream los cuales son usados para recibir un segundo collector que se aplica al resultado
		 * 
		 * Collectors.counting(): Esto aplica un count a cada elemento
		 * Collectors.reducing(): Es equivalente a la operación terminal reduce con la diferencia que usamos esto para reducciones de multilevel (O sea downstream)
		 * Collectors.partitioningBy(): Crea un Map<Boolean, List> con el resultado del predicado aplicado a cada elemento
		 * Collectors.mapping(): Nos permite aplicar una función a cada elemento y hacer un downstream de ese mismo resultado
		 */
		streamOfNames = Stream.of("Cosme Fulanito", "Pablo Marmol", "Pedro Picapiedra", "Super Sonico");
		Map<Integer, Long> downStreamCounting = streamOfNames.collect(Collectors.groupingBy(String::length, Collectors.counting()));
		
		streamOfNames = Stream.of("Cosme Fulanito", "Pablo Marmol", "Pedro Picapiedra", "Super Sonico");
		Map<Integer, Optional<String>> downStreamReducing = streamOfNames.collect(Collectors.groupingBy(String::length, Collectors.reducing((x, y) -> x + "," + y)));
		
		streamOfNames = Stream.of("Cosme Fulanito", "Pablo Marmol", "Pedro Picapiedra", "Super Sonico");
		Map<Integer, Map<Boolean, List<String>>> downStreamPartitioningBy = streamOfNames.collect(Collectors.groupingBy(String::length, Collectors.partitioningBy(x -> ((String) x).length() < 13)));
		
		streamOfNames = Stream.of("Cosme Fulanito", "Pablo Marmol", "Pedro Picapiedra", "Super Sonico");
		Map<Integer, Set<String>> downStreamMapping = streamOfNames.collect(Collectors.groupingBy(String::length, Collectors.mapping((x) -> x + " (" + x.length() + ")", Collectors.toSet())));
		
		System.out.println("Downstream counting: " + downStreamCounting);
		System.out.println("Downstream reducing: " + downStreamReducing);
		System.out.println("Downstream partitioning: " + downStreamPartitioningBy);
		System.out.println("Downstream mapping: " + downStreamMapping);
	}
	
	private static List<String> buildContainer() {
		return new ArrayList<>();
	}
	
	private static void combineContainers(List<String> left, List<String>right) {
		left.addAll(right);
	}
}