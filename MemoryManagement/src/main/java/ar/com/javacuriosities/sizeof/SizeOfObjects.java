package ar.com.javacuriosities.sizeof;

import ar.com.javacuriosities.utils.MemoryCounterAgent;

/*
 * Encabezado = 8 bytes.
 * age = 4 bytes.
 * code = 2 bytes.
 * objectReference = 4 bytes.
 * 8 + 4 + 2 + 4 = 18 bytes.
 * Alineamiento = 6 bytes.
 * SizeOf Bar = 18 + 6 = 24 bytes
 */
class Bar {
	private int age;
	private short code;
	private Integer objectReference;
}

/*
 * Al usar herencia la clase Foo no crea su propio encabezado.
 * 
 * Size del Padre (Sin incluir Padding) = 18 bytes. data = 4 bytes. currentDate
 * = 4 bytes. 18 + 4 + 4 = 26 bytes Alineamiento = 6 bytes. SizeOf Foo = 26 + 6
 * = 32 bytes
 */
class Foo extends Bar {
	private java.util.List<Object> data;
	private java.util.Date currentDate;
}

public class SizeOfObjects {

	public static void main(String[] args) {
		measure(new Object());
		measure(new Bar());
		measure(new Foo());
	}

	/*
	 * Este método recibe el objeto como par�metro y calcula su size.
	 */
	public static void measure(Object obj) {
		System.out.println(obj.getClass().getName() + ": Shallow size = "
				+ MemoryCounterAgent.sizeOf(obj));
	}
}