package ar.com.javacuriosities.lambdas;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/*
 * Se agrego el paquete java.util.function el cual contiene un conjunto
 * bien definido de Functional Interfaces.
 * Muchas de estas nuevas interfaces son usadas por el nuevo API de Streams
 */
public class Lesson03FunctionalInterfaces {
	public static void main(String[] args) {
		// Consumer<T>: Esta interfaz solo toma un valor y no retorna valor
		Consumer<String> consumer = (String message) -> System.out.println(message);
		
		// BiConsumer<T, U>: Esta interfaz toma dos valores y no tiene resultado
		BiConsumer<String, String> biconsumer = (String key, String value) -> System.out.println("Key: " + key + ", Value: " + value);
		
		// Supplier<T>: Esta interfaz es lo contrario de un consumer, esta no recibe parámetros y retorna un tipo genérico
		Supplier<String> supplier = () -> "Logging Message";
		
		// Function<T, R>: Esta interfaz puede ser asociada como una función matemática que toma un valor y devuelve otro (Este puede ser del mismo tipo o no)
		Function<String, Integer> functionLength = (String message) -> message.length();
		Function<String, String> functionToUpperCase = (String message) -> message.toUpperCase();
		
		// BiFunction<T, U, R>: Esta interfaz recibe dos parametros y retorna un valor
		BiFunction<String, String, String> functionConcatenate = (String left, String right) -> left + " " + right;
		
		// UnaryOperator<T>: Es una especialización de Function pero es un tipo especifico donde el argumento y el tipo de resultado son el mismo
		UnaryOperator<String> functionToLowerCase = (String message) -> message.toLowerCase();
		
		// BinaryOperator<T>: Es una especialización de BiFunction pero los dos argumentos y el tipo de resultado son el mismo
		BinaryOperator<String> functionLongestString = (String text1, String text2) -> {
			if (text1.length() > text2.length()) 
				return text1;
			return text2;
		};
		
		// Predicate: Es una especialización de Function que siempre retorna Boolean
		Predicate<Integer> isEven = (Integer number) -> number % 2 == 0;
		
		// BiPredicate: ES una especialización de BiFunction que toma dos parámetros y retorna Boolean
		BiPredicate<Integer, Integer> isGreaterThan = (Integer number, Integer lowerBound) -> number > lowerBound;
		BiPredicate<Integer, Integer> isLowerThan = (Integer number, Integer upperBound) -> number < upperBound;
		
		System.out.println("******************************");
		consumer.accept("Hello World");
		biconsumer.accept("Cosme", "Fulanito");
		System.out.println(supplier.get());
		System.out.println("Length: " + functionLength.apply("Hello World"));
		System.out.println("Upper Case: " + functionToUpperCase.apply("Hello World"));
		System.out.println("Concatenate: " + functionConcatenate.apply("Cosme", "Fulanito"));
		System.out.println("Lower Case: " + functionToLowerCase.apply("Hello World"));
		System.out.println("Longest String: " + functionLongestString.apply("Java", "Rocks"));
		System.out.println("Is Even: " + isEven.test(2));
		System.out.println("Is Greater Than: " + isGreaterThan.test(10, 100));
		System.out.println("Is Lower Than: " + isLowerThan.test(10, 100));
		
		/* 
		 * Además podemos usar distintos métodos como
		 * - andThen
		 * - compose 
		 * - or 
		 * - and
		 * - negate
		 */
		System.out.println("******************************");
		System.out.println(functionToLowerCase.andThen(functionToUpperCase).apply("Java Rocks"));
		
		System.out.println(functionToLowerCase.compose(functionToUpperCase).apply("Java Rocks"));
		
		System.out.println(isGreaterThan.or(isLowerThan).test(100, 10));
		
		System.out.println(isGreaterThan.and(isLowerThan).test(100, 10));
		
		System.out.println(isEven.negate().test(11));
	}
}