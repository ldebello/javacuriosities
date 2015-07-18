package ar.com.javacuriosities.lambdas;

import java.io.File;
import java.io.FileFilter;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/*
 * Las referencias a métodos nos permite reutilizar un método como una Lambda Expression.
 * El formato es "target_reference::method_name"
 */
public class Lesson04MethodAndConstructorReferences {
	public static void main(String[] args) {
		/*
		 * Esto es un Lambda complementamente valido pero dado que solo estamos
		 * llamando un método del parámetro podemos usar referencias a métodos
		 */
		FileFilter filter = (File f) -> f.canRead();

		// Hacemos lo mismo que antes pero con una referencia a método
		FileFilter enhancedFilter = File::canRead;

		System.out.println("Filter: " + filter.accept(new File(".")));
		System.out.println("Enhanced Filter: " + enhancedFilter.accept(new File(".")));
		
		/*
		 * Hay 3 tipos de referencias a métodos: 
		 * - Métodos estáticos 
		 * 		(args) -> ClassName.staticMethod(args) 
		 * 		ClassName::staticMethod
		 * 
		 * - Métodos de instancia de un objeto arbitrario de algún tipo 
		 *	 	(arg0, rest) -> arg0.instanceMethod(rest) 
		 *		ClassName::instanceMethod
		 * 
		 * - Métodos de una instancia en particular 
		 * 		(args) -> expr.instanceMethod(args) expr::instanceMethod
		 * 
		 * También podemos referirnos a this y dentro de un lambda nos estamos refiriendo
		 * al objeto que contiene el lambda.
		 */

		// Static Method Reference
		Function<Person, String> toJsonFunction = Person::toJson;

		// Instance method of an arbitrary object of a particular type
		Function<Person, String> readNameFunction = Person::getName;

		// Instance method of a particular object
		Person person = new Person("Cosme Fulanito", 99);
		PersonValidator validator = new PersonValidator();

		Predicate<Person> validateAge = validator::checkAge;

		System.out.println("******************************");
		System.out.println("Json Format: " + toJsonFunction.apply(person));
		
		System.out.println("Name: " + readNameFunction.apply(person));
		
		System.out.println("Validate Age: " + validateAge.test(person));
		
		/*
		 * Además podemos tener referencias a constructores
		 * 		(args) -> new ClassName(args)
		 * 		ClassName::new
		 */
		BiFunction<String, Integer, Person> personFactory = Person::new;
		
		System.out.println(toJsonFunction.apply(personFactory.apply("Pablo Marmol", 404)));
	}

	private static final class Person {

		private String name;
		private Integer age;

		public Person(String name, Integer age) {
			this.name = name;
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public Integer getAge() {
			return age;
		}

		public static String toJson(Person person) {
			return "{\"Person\":{\"name\":\"" + person.name + "\"}}";
		}
	}

	private static final class PersonValidator {
		public boolean checkAge(Person person) {
			return person.getAge() > 18;
		}
	}
}