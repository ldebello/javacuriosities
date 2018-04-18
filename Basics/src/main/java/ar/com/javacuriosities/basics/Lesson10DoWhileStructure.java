package ar.com.javacuriosities.basics;

public class Lesson10DoWhileStructure {

    public static void main(String[] args) {
        System.out.println("Lesson 10 - Do While Structure");

        /*
         * Esta estructura sirve para ejecutar una porción de código, por un determinado numero de veces,
         * al final de cada iteración se chequea si la condición sigue siendo valida
         */

        // ********************************** //
        // Ciclo do while de 0 hasta 10
        // ********************************** //

        // Definimos una variable que sirve para controlar el ciclo
        int counter1 = 0;

        // Aquí el ciclo do while primero imprime el numero y luego chequea la condición
        do {
            System.out.println(counter1);
            counter1++; // Aquí incrementamos el valor en 1
        } while (counter1 <= 10); // No olvidar el ; al final porque sino no compila

        // Debemos tener cuidado al imprimir el valor del contador después del ciclo ya que queda incrementado/decrementado una vez mas
        System.out.println("Counter 1: " + counter1);

        // ********************************** //
        // Ciclo do while de 10 hasta 0
        // ********************************** //

        // Definimos una variable que sirve para controlar el ciclo, aquí la inicializo en 10 porque la cuenta es regresiva
        int counter2 = 10;

        // Aquí el ciclo do while imprime el valor y después chequea que la variable sigue siendo menor o igual a 10
        do {
            System.out.println(counter2);
            counter2--; // Aquí decrementamos el valor en 1
        } while (counter2 >= 0);

        // ********************************** //
        // Ciclo do while infinito
        // Los ciclos infinito son el error mas común y debemos estar atentos a estos casos porque pueden tildar nuestros
        // programas o servidores.
        // ********************************** //

        // Hago un ciclo que su condición siempre es true
        /*
         do {
         System.out.println("Infinite Loop");
         } while (true);
         */

        /*
         * Los ciclos Do...While siempre se ejecutan al menos una vez
         */
    }
}
