package ar.com.javacuriosities.runtime_data_areas;

public class HelloWorld {
	/*
	 * La constante HELLOWORLD es almacenada en el Method Area porque forma
	 * parte de la clase HelloWorld. La cadena "Hello World" es salvada en el
	 * "Runtime Constant Pool" porque se encuentra definida en forma de literal
	 */
	private static final String HELLOWORLD = "Hello World";

	/*
	 * El Thread-Main contiene su propio PC(Program Counter) el cual nos vas a
	 * ir guiando linea a linea por nuestro código.
	 */
	public static void main(String[] args) {
		/*
		 * En esta linea estamos usando el operador new por lo cual se va a
		 * reservar espacio en el Heap para el Objeto HelloWorld pero la
		 * variable helloWorld va a ser almacenada en el Stack del Thread-Main.
		 * Como este objeto no es muy pesado, va a ser almacenado en el Eden.
		 */
		HelloWorld helloWorld = new HelloWorld();
		helloWorld.print();

		/*
		 * Aquí estamos de vuelta usando el operador new por lo cual se va
		 * reservar el espacio en el Heap para guardar un Objeto String. Al
		 * igual que antes la variable goodbye se va a almacenar en el stack
		 * pero va a estar apuntando al Heap.
		 */
		String goodbye = new String("Goodbye");
		System.out.println(goodbye);
	}

	/*
	 * Toda la información sobre la clase, propiedades y métodos va a ser
	 * almacenada en el Method Area
	 */
	public void print() {
		System.out.println(HELLOWORLD);
	}
}
