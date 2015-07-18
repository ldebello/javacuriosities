package ar.com.javacuriosities.lambdas;


/*
 * Características:
 * - Dentro de un lambda podemos arrojar excepciones
 * - Lambdas de una línea no necesitan llaves
 * - Lambdas de una línea no necesitan un return 
 * - Lambdas de un solo parámetro no necesitan paréntesis
 * - Lambdas sin parámetros debe contener paréntesis vacíos
 * 
 * () -> System.out.println("Hello World")
 * x -> x + 10
 * (int x, int y) -> { return x + y; }
 * (String x, String y) -> x.length() - y.length()
 * (int x) -> {
 * 	int y = 10;
 * 	return x + y;
 * }
 * 
 * Una Lambda Expression puede ser usada donde el tipo es una Functional Interface
 * 
 * Functional Interface (ASM --> Abstract Single Method)
 * - Tiene que ser una interfaz
 * - Es una interfaz con un solo método abstracto
 * - El Lambda es provisto como implementación del método abstracto
 * - Esta puede estar anotada con la annotation @FunctionalInterface la cual le dice al compilar que validé que esa interfaz es valida como Functional Interface
 */
public class Lesson02Features {
	public static void main(String[] args) {
		/*
		 * Aca estamos usando nuestra propia Functional Interface pero muchas interfaces del 
		 * JDK cumplen con esto contrato así como muchas otras fueron agregadas
		 */
		MyOwnFunctionalInterface methodImplementation = () -> System.out.println("Hello World");
		
		methodImplementation.execute();
		methodImplementation.defaultBehaviour();
		MyOwnFunctionalInterface.staticBehaviour();
	}
	
	@FunctionalInterface
	private interface MyOwnFunctionalInterface {
		
		public void execute();
		
		/*
		 * Hasta Java 1.7 las interfaces solo podan tener métodos abstractos y atributos estáticos,
		 * pero esto cambio a partir de Java 1.8 donde puede tener métodos marcados como default (Esto 
		 * fue agregado para no romper implementaciones existentes)
		 */
		default public void defaultBehaviour() {
			System.out.println("This method is inherited for all classes which implements this interface");
		}
		
		public static void staticBehaviour() {
			System.out.println("Now interfaces can have static methods");
		}
	}
}