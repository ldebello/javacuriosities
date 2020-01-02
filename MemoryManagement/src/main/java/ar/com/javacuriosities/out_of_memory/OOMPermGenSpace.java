package ar.com.javacuriosities.out_of_memory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/*
 * Estamos almacenando los String en el "Runtime Constant Pool" y como son Strings muy grandes
 * empiezan a llenar el PermGen
 */
public class OOMPermGenSpace {

    public static void main(String[] args) {
        int id = 0;
        try {
            // Guardamos los n�meros que vamos generando
            List<String> numbers = new ArrayList<String>();

            /*
             * Elevamos el numero 1000 a la potencia 1000 y generamos un String,
             * eso genera un String muy grande
             */
            String bigString = new BigDecimal(1000).pow(1000).toString();

            /*
             * Insertamos el resultado de la operación + un contador que siempre
             * incrementa uno
             */
            for (int i = 0; i < 50000; i++) {
                numbers.add((bigString + id++).intern());
            }
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
    }
}