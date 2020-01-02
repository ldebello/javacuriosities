package ar.com.javacuriosities.garbage_collector;

import java.util.ArrayList;
import java.util.List;

public class GCDetails {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 100000; i++) {
            list.add("Cadena " + i);
        }

        for (int i = 0; i < 100000; i++) {
            String data = "Cadena " + i;
        }

        for (int i = 0; i < 100000; i++) {
            list.add("Cadena " + i);
        }

        System.gc();

        for (int i = 0; i < 100000; i++) {
            String data = "Cadena " + i;
        }
    }
}