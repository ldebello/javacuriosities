package ar.com.javacuriosities.streams;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

/*
 * La JDK provee muchas Terminal operations (average, sum, min, max, and count) que retornan 
 * un valor combinando el contenido del stream. Este tipo de operaciones es llamado
 * reduction. 
 * También encontraremos reducciones que retornan una collection en lugar de un solo valor, muchas
 * reducciones ejecutan una tarea especifica como obtener el average de una secuencia de elementos
 * o agrupar elementos en categorías.
 * Java provee métodos de propósito general como
 * 
 *  - Stream.reduce
 *  - Stream.collect
 */
public class Lesson07Reductions {
	public static void main(String[] args) {
		try {
			/*
			 * Para analizar el reduce usaremos el siguiente ejemplo, donde
			 * debemos obtener la longitud de la línea mas larga, o sea tenemos
			 * una secuencia con las líneas y queremos reducir esto a un valor
			 * que contiene la longitud de la línea mas larga
			 */
			System.out.println("Finding the length of the longest line in a file");
			
			URL resource = Thread.currentThread().getContextClassLoader().getResource("data/lines.txt");
			Path input = Paths.get(resource.toURI());
			
			// Se obtiene la longitud de cada línea y luego el máximo del stream generado
			int longestLineLength = Files.lines(input).mapToInt(String::length).max().getAsInt();
			System.out.println("The size of the longest line is: " + longestLineLength);
			
			/*
			 * La solución anterior es simple y nos permite cumplir nuestro objetivo pero imaginemos
			 * que tenemos que obtener la línea mas larga en lugar de su longitud.
			 * La siguiente implementación es una forma simple de hacerlo su mayor problema radica
			 * en que debemos ordenar el stream para quedarnos con la linea mas larga, lo cual
			 * no es bueno si nuestro stream es muy grande
			 */
			System.out.println("Find the longest line in a file");
			String longestLine = Files.lines(input).sorted((x, y) -> y.length() - x.length()).findFirst().get();
			System.out.println("Version 1- The longest line is: " + longestLine);
			
			/*
			 * Si analizamos el ejercicio y pensamos en forma imperativa veremos que la forma
			 * de hacer esto es mediante un loop donde en la iteración de cada línea preguntamos su 
			 * longitud y la comparamos con la línea mas larga hasta el momento, de aquí podemos
			 * extraer los dos siguientes conceptos
			 * 
			 * 	- External loops (El ciclo que ejecutamos para recorrer cada línea)
			 * 	- Keep mutable state (La variable que va conteniendo la línea mas larga)
			 * 
			 * Dado que Java 8 incluye todo este gran set de herramienta para programación funcional
			 * veamos como resolver esto de una forma que no sea imperativa (Esto no quiere decir
			 * que el estilo funcional siempre sea el correcto, siempre debemos analizar nuestras opciones
			 * y ver que nos conviene aplicar)
			 * 
			 * Si vemos de Stream API nos encontraremos con un método llamado reduce(), el cual 
			 * toma un valor parcial y el siguiente valor de nuestro stream para retornar el siguiente
			 * resultado parcial así hasta finalizar el stream, si analizamos esto veremos que esto
			 * tiene varias ventajas
			 * 
			 * 	- Internal loops (Ahora el recorrido es realizado por Stream API lo que me permite una forma fácil de paralizar) 
			 * 	- No mutable state (Por que el acumulador es siempre recibido por la función y se retorna el mismo o uno nuevo)
			 *  - Laziness (Iremos consumiendo línea por línea sin tener todas en memoria)
			 */
			longestLine= Files.lines(input).reduce((acc, curr) -> {
				if (acc.length() > curr.length()) {
					return acc;
				}
				return curr;
			}).get();
			System.out.println("Version 2- The longest line is: " + longestLine);
			
			// Otra solución posible es usando una version especializada de max()
			longestLine = Files.lines(input).max(Comparator.comparingInt(String::length)).get();
			System.out.println("Version 3- The longest line is: " + longestLine);
			
		} catch (Exception e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}
}
