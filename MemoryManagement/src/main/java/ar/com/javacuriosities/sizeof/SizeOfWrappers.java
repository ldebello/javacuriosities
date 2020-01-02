package ar.com.javacuriosities.sizeof;

import ar.com.javacuriosities.utils.MemoryCounterAgent;

public class SizeOfWrappers {

	public static void main(String[] args) {
		measure(new Character('A'));
		measure(new Byte((byte) 1));
		measure(new Short((short) 1));
		measure(new Integer(1));
		measure(new Long(1));
		measure(new Float(1.0));
		measure(new Double(1.0));
		measure(new Boolean(true));
	}

	/*
	 * Este método recibe el objeto como par�metro y calcula su size.
	 */
	public static void measure(Object obj) {
		System.out.println(obj.getClass().getName() + ": Shallow size = "
				+ MemoryCounterAgent.sizeOf(obj));
	}
}
