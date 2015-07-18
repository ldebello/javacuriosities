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
	}
}
