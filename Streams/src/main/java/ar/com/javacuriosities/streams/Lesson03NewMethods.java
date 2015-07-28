package ar.com.javacuriosities.streams;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
 * En Java 1.8 se agregaron un total de 95 en 23 clases, los cuales podemos usar
 * para construir Streams
 */
public class Lesson03NewMethods {
	public static void main(String[] args) {
		try {
			/*
			 * Collection Interface: Posee dos métodos - stream(): Crea un
			 * Stream en base a la collection - parallelStream(): Un Stream
			 * paralelo por medio del framework Fork/Join
			 */
			List<String> words = new ArrayList<>();

			words.add("Hello");
			words.add("Streams");

			words.stream();
			words.parallelStream();

			// Se agrego el método stream() en la clase Arrays, solo provee
			// streams secuenciales
			IntStream numbers = Arrays.stream(new int[] { 1, 2, 3, 4, 5 });

			numbers.forEach(System.out::println);

			/*
			 * La clase Files contiene varios métodos que generan Streams
			 */

			System.out.println("Reading files in directory");
			URL resourceUrl = Thread.currentThread().getContextClassLoader().getResource("data");
			try (Stream<Path> paths = Files.list(Paths.get(resourceUrl.toURI()))) {
				paths.forEach(System.out::println);
			}

			System.out.println("Reading lines from file");
			resourceUrl = Thread.currentThread().getContextClassLoader().getResource("data/lines.txt");
			try (Stream<String> lines = Files.lines(Paths.get(resourceUrl.toURI()))) {
				lines.forEach(System.out::println);
			}

			System.out.println("Walking directory");
			resourceUrl = Thread.currentThread().getContextClassLoader().getResource("data");
			try (Stream<Path> paths = Files.walk(Paths.get(resourceUrl.toURI()), FileVisitOption.FOLLOW_LINKS)) {
				paths.forEach(System.out::println);
			}
			
			// Se agrego el método lines() a la clase BufferedReader
			System.out.println("Reading lines from line using BufferedReader");
			resourceUrl = Thread.currentThread().getContextClassLoader().getResource("data/lines.txt");
			try (BufferedReader reader = new BufferedReader(new FileReader(new File(resourceUrl.toURI())))) {
				Stream<String> lines = reader.lines();
				lines.forEach(System.out::println);
			}
			
			// Se agrego el método splitAsStream a la clase Pattern
			System.out.println("Using splitAsStream method");
			Pattern pattern = Pattern.compile(",");
			Stream<String> splitAsStream = pattern.splitAsStream("Hello,Streams,Java");
			splitAsStream.forEach(System.out::println);
			
			// Se agrego el método chars en la interface CharSequence
			System.out.println("Converting chars as int in a CharSequence");
			String message = "Hello";
			IntStream chars = message.chars();
			chars.forEach(System.out::println);
			
			// Tenemos varios métodos estáticos en la interfaz Stream
			Stream<String> firstPart = Stream.of("Hello");
			Stream<String> secondPart = Stream.of(" ");
			Stream<String> thirdPart = Stream.of("World");
			
			Stream<String> partialStrem = Stream.concat(firstPart,  secondPart);
			Stream<String> finalMessage = Stream.concat(partialStrem,  thirdPart);
			finalMessage.forEach(System.out::print);
			
			// Podemos generar rangos usando IntStream y el método range()
			IntStream range = IntStream.range(0, 10);
			range.forEach(System.out::println);
		} catch (Exception e) {
			// Log and Handle exception
			e.printStackTrace();
		}		
	}
}