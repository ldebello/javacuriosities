package ar.com.javacuriosities.streams;

import java.util.Optional;

/*
 * La clase Optional fue agregada en Java 1.8 y además de ser usada en de Streams API, puede
 * ser muy provechoso para evitar problemas como NPE (NullPointerException)
 */
public class Lesson06Optional {
	public static void main(String[] args) {
		System.out.println("Common Java Code");
		
		Student student = createStudent();

		// En muchos casos como estos debemos validar que la referencia
		// retornada no sea Null
		if (student != null) {
			System.out.println("Student: " + student.getName());

			// Luego si queremos obtener la dirección también debemos chequear
			// por Null
			Address address = student.getAddress();
			if (address != null) {
				System.out.println("Address: " + address);
				
				// Luego si queremos obtener el teléfono asociado a esa dirección
				Telephone telephone = address.getTelephone();
				if (telephone != null) {
					System.out.println("Telephone: " + telephone);
				}
			}
		}
		
		System.out.println("Java Code with Optional");
		
		/*
		 * Una forma de mejorar el código de arriba es indicando que nuestra función puede
		 * retornar Null de forma explícita, esto lo podemos hacer por medio de Optional lo
		 * cual es un contenedor a una referencia y podemos utilizar sus métodos auxiliares 
		 */
		Optional<Student> optionalStudent = createOptionalStudent();
		
		// Podemos chequear si hay una referencia por medio del método ifPresent()
		optionalStudent.ifPresent(s -> System.out.println("Student: " + s.getName()));
		
		// Aquí estamos verificando una condición sobre nuestra referencia, este código es similar a "if (student != null && student.getAge() > 18)" 
		optionalStudent.filter(s -> s.getAge() > 18).ifPresent(s -> System.out.println("Age: " + s.getAge()));
		
		Optional<Student> optionalStudentWithAddress = createOptionalStudentWithAddress();
		
		/* 
		 * Usando la utilidad de map podemos encadenar operaciones y eliminar la redundancia al validar el código contra Null, este código es similar a:
		 * if (student != null) {
		 * 		if (student.getAddress() != null) {
		 * 			if(student.getAddress().getTelephone() != null) {
		 * 				System.out.println("Telephone: " + student.getAddress().getTelephone());
		 * 			}
		 * 		}
		 * }
		 */
		optionalStudentWithAddress.map(Student::getAddress).map(Address::getTelephone).ifPresent(t -> System.out.println("Telephone: " + t));
		
		Optional<Student> nullStudent = createNullStudent();
		
		// Podemos usar el método orElse para recibir la referencia contenida en el Optional o una creada en el momento en caso de que fuera Null
		System.out.println("Default Student: " + nullStudent.orElse(new Student("Pedro Picapiedra", 99)));
	}

	private static Student createStudent() {
		return new Student("Cosme Fulanito", 99);
	}

	private static Optional<Student> createNullStudent() {
		return Optional.ofNullable(null);
	}
	
	private static Optional<Student> createOptionalStudent() {
		// Para crear un Optional podemos usar los métodos of o ofNullable dependiendo si sabemos si la referencia puede ser Null o no
		return Optional.of(new Student("Cosme Fulanito", 99));
	}
	
	private static Optional<Student> createOptionalStudentWithAddress() {
		return Optional.of(new Student("Cosme Fulanito", 99, new Address("Street Leader",
				1234, new Telephone("12345678", "Home"))));
	}
	

	private static final class Student {
		private String name;

		private int age;

		private Address address;

		public Student(String name, int age) {
			super();
			this.name = name;
			this.age = age;
		}

		public Student(String name, int age, Address address) {
			super();
			this.name = name;
			this.age = age;
			this.address = address;
		}

		public String getName() {
			return name;
		}

		public int getAge() {
			return age;
		}

		public Address getAddress() {
			return address;
		}

		@Override
		public String toString() {
			return "Student [name=" + name + ", age=" + age + ", address="
					+ address + "]";
		}
	}

	private static final class Address {
		private String street;

		private int number;

		private Telephone telephone;

		public Address(String street, int number, Telephone telephone) {
			super();
			this.street = street;
			this.number = number;
			this.telephone = telephone;
		}
		
		public Telephone getTelephone() {
			return telephone;
		}

		@Override
		public String toString() {
			return "Address [street=" + street + ", number=" + number
					+ ", telephone=" + telephone + "]";
		}
	}

	private static final class Telephone {

		private String number;

		private String type;

		public Telephone(String number, String type) {
			super();
			this.number = number;
			this.type = type;
		}

		@Override
		public String toString() {
			return "Telephone [number=" + number + ", type=" + type + "]";
		}
	}
}