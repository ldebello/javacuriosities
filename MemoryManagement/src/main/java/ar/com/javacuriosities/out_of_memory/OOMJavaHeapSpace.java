package ar.com.javacuriosities.out_of_memory;

import java.util.HashMap;
import java.util.Map;

/*
 * Se est�n guardando demasiados objetos en una estructura de datos que nunca se puede liberar
 */
public class OOMJavaHeapSpace {

    private final static int MAX_ITERATIONS = 500000000;

    /*
     * Creamos un String que pesa ~1 KB.
     */
    private final static String LEAKING_DATA_PREFIX = "datadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadata";

    // Definimos una variable estática para guardar la información
    private static Map<String, String> leakingMap;

    static {
        leakingMap = new HashMap<String, String>();
    }

    public static void main(String[] args) {
        try {
            for (int i = 0; i < MAX_ITERATIONS; i++) {

                String data = LEAKING_DATA_PREFIX + i;

                // Agregamos los datos al Map
                leakingMap.put(data, data);
            }

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}