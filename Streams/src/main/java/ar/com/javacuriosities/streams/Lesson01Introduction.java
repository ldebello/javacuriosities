package ar.com.javacuriosities.streams;

import java.util.stream.Stream;

/*
 * A partir de Java 1.8 podemos utilizar la nueva API de Streams.
 * Cuando hablamos de Streams nos referimos a una secuencia de elementos, donde los elementos van fluyendo
 * y se van.
 * Algunas de sus características son:
 * 	- Sólo pueden recorrerse una sola vez.
 * 	- Pueden ser infinitos.
 * 	- Pueden ser secuenciales o paralelos
 * 	- Están compuestos de operaciones intermedias que retornan streams y operaciones finales que desencadenan todo el flujo
 * 
 * Podemos asociar los Streams con Pipeline, donde debe existir una fuente de datos, 
 * luego 0 o mas operaciones intermedias, las cuales retornan flujos que sirven como entrada de las 
 * siguientes operaciones, y al final aplicamos una operación final la cual produce un resultado o 
 * un side-effect (Puede ser algo tan simple como imprimir los valores) 
 */
public class Lesson01Introduction {
	
	public static void main(String[] args) {
		// Creamos un Stream el cual recorre sus valores y los imprime
		Stream<String> names = Stream.of("Cosme Fulanito", "Pablo Marmol", "Pedro Picapiedra");
		names.forEach(System.out::println);
		
		// Creamos un Stream donde aplicamos operaciones intermedias y luego una operación final
		Stream<String> words = Stream.of("hello", "world", "streams");
		
		// Operación intermedia
		words = words.map(word -> word.substring(0, 1).toUpperCase() + word.substring(1));
		
		// Operación final
		words.forEach(System.out::println);
	}
}
