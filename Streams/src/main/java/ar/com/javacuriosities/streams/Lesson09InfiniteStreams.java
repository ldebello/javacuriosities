package ar.com.javacuriosities.streams;

import java.util.Random;
import java.util.stream.IntStream;

/*
 * Los Streams pueden ser finitos o infinito, generalmente pensamos esto como:
 * 
 * while(true) {
 * 	
 * 	if (specialCondition()) {
 * 		break;
 * 	}
 * 	// Loop indefinitely
 * }
 * 
 * Dado que cuando usamos Streams no hay un ciclo de forma explícita no podemos aplicar algo similar
 * a lo anterior pero podemos terminar un Stream cuando se cumple una condición
 * 
 * 	- findFirst (Este método sirve con sequential y parallel stream, siempre ofreciendo el mismo resultado para el mismo input)
 * 	- findAny (Es útil para parallel stream ya que no siempre obtendremos el mismo resultado)
 */
public class Lesson09InfiniteStreams {
	public static void main(String[] args) {
		/* 
		 * Este Stream es infinito, podemos usar el método filter que retorna un LazyStream y luego findFirst o findAny
		 */
		IntStream randonNumbers = new Random().ints();
		int number = randonNumbers.filter(i -> i > 0 && i < 256).findFirst().getAsInt();
		System.out.println("The random number is: " + number);
	}
}
