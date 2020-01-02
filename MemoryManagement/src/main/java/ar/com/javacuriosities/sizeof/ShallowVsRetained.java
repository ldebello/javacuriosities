package ar.com.javacuriosities.sizeof;

import java.util.ArrayList;
import java.util.List;

import ar.com.javacuriosities.utils.MemoryCounterAgent;

public class ShallowVsRetained {

	public static void main(String[] args) {
		/*
		 * Shallow size ArrayList = 
		 * 8 (Header) + 
		 * 4 (Atributo size int primitivo) + 
		 * 4 (Atributo modCount int primitivo ) + 
		 * 4 (Referencia al array) = 20 bytes +
		 * 4 (Padding) = 24 bytes
		 * 
		 * 
		 * Retained size ArrayList =
		 * Shallow size +
		 * Shallow size (elementData) = 24 + 52 = 76 + 4 (Padding) = 80 bytes
		 * 
		 * 
		 * Shallow size (elementData) = 
		 * 8 (Header) +
		 * 4 (Atributo del length) +
		 * 4 * Length del array (Al principio vale 10) =
		 * 52 bytes
		 */
		List<Integer> listOfNumbers = new ArrayList<Integer>();
 	
		System.out.println("Memoria Inicial");
		measure(listOfNumbers);
		
		System.out.println("Memoria después de insertar el primer elemento");
		listOfNumbers.add(new Integer(1));
		measure(listOfNumbers);
		
		System.out.println("Memoria después de insertar el segundo elemento");
		listOfNumbers.add(new Integer(2));
		measure(listOfNumbers);
		
		System.out.println("Memoria después de insertar el tercer elemento");
		listOfNumbers.add(new Integer(3));
		measure(listOfNumbers);
		
		System.out.println("Memoria después de insertar el cuarto elemento");
		listOfNumbers.add(new Integer(4));	
		measure(listOfNumbers);
	}

	public static void measure(Object obj) {
		System.out.println(obj.getClass().getName() + ": Shallow size = "
				+ MemoryCounterAgent.sizeOf(obj) + " Retained size = "
				+ MemoryCounterAgent.deepSizeOf(obj));
	}
}