package ar.com.javacuriosities.sizeof;

public class SizeOfPrimitives {
	public static void main(String[] args) {
		/*
		 * Definimos distintas variables que serán almacenadas en el Stack del
		 * Thread-Main
		 */
		char variable1 = 'A';
		byte variable2 = 1;
		short variable3 = 1;
		int variable4 = 1;
		long variable5 = 1;
		float variable6 = 1.0F;
		double variable7 = 1.0;
		boolean variable8 = true;

		/*
		 * Los char ocupan dos bytes porque pueden almacenar cualquier carácter
		 * unicode
		 */
		System.out.println("char: " + 2 + " Bytes");
		System.out.println("byte: " + 1 + " Bytes");
		System.out.println("short: " + 2 + " Bytes");
		System.out.println("int: " + 4 + " Bytes");
		System.out.println("long: " + 8 + " Bytes");
		System.out.println("float: " + 4 + " Bytes");
		System.out.println("double: " + 8 + " Bytes");

		/*
		 * Los boolean son tienen dos valores posibles true o false y a niveles
		 * prácticos podemos asumir que ocupan un byte, pero la pagina oficial
		 * de Oracle dice que el size no es algo que este definido precisamente
		 * para este tipo de datos
		 */
		System.out.println("boolean: " + 1 + " Bytes");
	}
}
