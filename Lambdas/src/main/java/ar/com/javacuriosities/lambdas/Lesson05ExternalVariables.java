package ar.com.javacuriosities.lambdas;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/*
 * Desde una Lambda Expression podemos referenciar variables externas siempre y cuando
 * estas sean "effectively final".
 * Una variable "effectively final" es aquella que cumple los requerimientos para ser marcada como final
 * pero no lo es
 */
public class Lesson05ExternalVariables {

	public static void main(String[] args) {
		String message = "Hello World";

		// Aca creamos un lambda que esta referenciando a la variable externa
		// message
		Supplier<String> messageProvider = () -> message;

		System.out.println(messageProvider.get());

		List<String> words = Arrays.asList("Welcome", "Lambdas", "to", "Java");
		
		WordCounter wordCounter = new WordCounter();
		wordCounter.count(words);
		System.out.println(wordCounter.getCounter());
	}

	private static final class WordCounter {
		private int counter;

		public WordCounter() {
		}

		public void count(List<String> data) {
			/*
			 * Además podemos referenciar variables de instancia dado que dentro de un
			 * Lambda this se refiere al objeto que contiene al Lambda, en el siguiente
			 * ejemplo se puede ver como invocamos "counter++" desde nuestro Lambda, aunque
			 * al principio esto puede sonar contradictorio porque los Lambda necesitan referenciar
			 * "effectively final" variables y aqui vemos que incrementamos la variable, lo que sucede
			 * Aquí es que el compilar automáticamente transforma el código a "this.counter++" y la variable
			 * this es "effectively final"
			 */
			data.forEach(value -> counter++);
		}

		public int getCounter() {
			return counter;
		}
	}
}