package ar.com.javacuriosities.heap_dump;

import javax.swing.SwingUtilities;

public class MainHeapDump {

	public static void main(String[] args) {
		// Iniciamos la UI en el EDT (Event Dispatcher Thread)
		SwingUtilities.invokeLater(() -> new HeapDump());
	}

}
