package ar.com.javacuriosities.memory_usage;

import javax.swing.*;

public class MainMemoryUsage {

    public static void main(String[] args) {
        // Iniciamos la UI en el EDT (Event Dispatcher Thread)
        SwingUtilities.invokeLater(() -> new Form());
    }

}
