package ar.com.javacuriosities.lambdas;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * Lambda Expressions fue incluido en Java 1.8 y nos proveen una forma simple de representar
 * funciones anónimas, por medio de esto logramos código mas legible, abstracto y menos propenso a errores.
 * Además son ampliamente usado en Streams lo cual es otra característica agregada en Java 1.8.
 * 
 */
public class Lesson01Introduction {
	public static void main(String[] args) {
		// Generamos datos random
		Random random = new SecureRandom();
		
		List<Integer> numbers = new ArrayList<>();
		
		for (int i = 0; i < 100; i++) {
			numbers.add(random.nextInt(50000));
		}
		
		System.out.println("Initial State: " + numbers);
		
		/*
		 * Aca estamos usando una Lambda Expression 
		 * Como se puede ver no definimos el tipo de datos de el parámetro
		 * left & right esto se debe a que además se mejoro la inferencia de tipos
		 * y como sort recibe un comparator con el generic type de la lista ya detecta
		 * que solo pueden ser Integers.
		 * La forma de hacer esto mismo en Java 1.7 era utilizando una clase anónima o creando
		 * una clase que implementaba el Comparator.
		 * Es importante aclarar que los Lambda Expression representa un método pero no son 
		 * asociados con una clase por lo cual reciben el nombre de funciones anónimas
		 */
		numbers.sort((left, right) -> left.compareTo(right));
		
		System.out.println("Final State: " + numbers);
		
		/*
		 * Ahora podemos revisar otro detalle sobre los lambda dado que cuando
		 * compilamos nuestra clase no vemos ninguna clase anónima o algo similar surge la duda
		 * como los lambdas están definido
		 * Para esto podemos usar el comando "javap -p <ClassName>.class"
		 * Si hacemos esto veremos que nuestra clase tiene métodos del estilo
		 * 
		 * private static void lambda$X$Y()
		 * 	- Donde x representa el nombre del método que contiene el lambda
		 * 	- Donde y representa un numero de secuencia iniciado en 0
		 * 
		 * Luego en tiempo de ejecuccion esto de resuelve con una instrucción a nivel de bytecode,
		 * llamada invokedynamic, esta fue incluida en Java 1.7
		 * Durante el runtime se genera bytecode on the fly para ejecutar estos métodos, eso hace uso
		 * de sun.misc.Unsafe.defineAnonymousClass() y podemos ver las clases generadas con el siguiente 
		 * parámetro "-Djdk.internal.lambda.dumpProxyClasses=<path>"
		 */
		Runnable lambda = () -> System.out.println("Hello World");
		
		lambda.run();
	}
}
