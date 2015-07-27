package ar.com.javacuriosities.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

/*
 * Dado que Java es orientado a objetos tenemos algunos tipos primitivos, a partir de Java 1.5
 * podemos ir de un tipo primitivo a objeto sin ningún esfuerzo dado que se agrego lo que se conoce
 * como autoboxing and unboxing, pero esto puede afectar la performance.
 * Si analizamos como esto puede afectar los Streams veremos que solo podemos almacenar objetos lo cual 
 * genera que si usamos tipos primitivos estaremos haciendo boxing and unboxing para cada elemento lo cual 
 * agrega un overhead innecesario, por esto mismo además del tipo Stream hay soporte para los tipos 
 * DoubleStream, IntStream y LongStream
 * 
 */
public class Lesson02PrimitiveStreams {
	
	public static void main(String[] args) {
		List<Student> students = new ArrayList<>();
		students.add(new Student(2014, 0));
		students.add(new Student(2015, 3));
		students.add(new Student(2015, 6));
		students.add(new Student(2015, 9));
		students.add(new Student(2014, 12));
		
		// Aquí en lugar de ejecutar el método map el cual retorna un Stream, usamos el método mapToInt el cual retorna un IntStream
		OptionalInt maximumNumberOfCourses = students.stream().filter(s -> s.getYear() == 2015).mapToInt(s -> s.getNumberOfCourses()).max();
		
		// Aquí usamos la clase Optional la cual representa un valor que puede ser nulo
		maximumNumberOfCourses.ifPresent(number -> System.out.println("The maximum number is: " + number));
	}

	private static final class Student {
		private int year;

		private int numberOfCourses;

		public Student(int year, int numberOfCourses) {
			super();
			this.year = year;
			this.numberOfCourses = numberOfCourses;
		}

		public int getYear() {
			return year;
		}

		public int getNumberOfCourses() {
			return numberOfCourses;
		}

	}
}
