package ar.com.javacuriosities.heap_dump;

import java.util.ArrayList;
import java.util.List;

public class HeapDumpPath {

	private static List<String> information = new ArrayList<>();

	public static void main(String[] args) {
		for (int i = 0; i < 900000; i++) {
			String data = "Cadena " + i;
			information.add(data);
		}
	}
}
