package ar.com.javacuriosities.basics;

public class Lesson09WhileStructure {

    public static void main(String[] args) {
        System.out.println("Lesson 09 - While Structure");

        /*
         * Esta estructura sirve para ejecutar una porción de código, por un determinado numero de veces,
         * por cada iteración se chequea si la condición sigue siendo valida
         */

        // ********************************** //
        // Ciclo while de 0 hasta 10
        // ********************************** //

        // Definimos una variable que sirve para controlar el ciclo
        int counter1 = 0;

        // Aquí el ciclo while en cada iteración chequea que la variable sigue siendo menor o igual a 10
        while (counter1 <= 10) {
            System.out.println(counter1);
            counter1++; // Aqui incrementamos el valor en 1
        }

        // Debemos tener cuidado al imprimir el valor del contador después del ciclo ya que queda incrementado/decrementado una vez mas
        System.out.println("Counter 1: " + counter1);

        // ********************************** //
        // Ciclo while de 10 hasta 0
        // ********************************** //

        // Definimos una variable que sirve para controlar el ciclo, aquí la inicializo en 10 porque la cuenta es regresiva
        int counter2 = 10;

        // Aquí el ciclo while en cada iteración chequea que la variable sigue siendo menor o igual a 10
        while (counter2 >= 0) {
            System.out.println(counter2);
            counter2--; // Aquí decrementamos el valor en 1
        }

        // ********************************** //
        // Ciclo while infinito
        // Los ciclos infinito son el error mas común y debemos estar atentos a estos casos porque pueden tildar nuestros
        // programas o servidores.
        // ********************************** //

        // Hago un ciclo que su condición siempre es true
        /*
         while (true) {
         System.out.println("Infinite Loop");
         }
         */


        /*
         // Este ciclo parece normal pero si miramos el while, encontramos un ; y eso nos genera un ciclo infinito
         int counter3 = 0;
         while (counter3 <= 10); {
         System.out.println("Seems a normal Loop");
         counter3++; // Aca se incrementa el valor
         }
         */


        // ********************************** //
        // Ciclo while que nunca se ejecuta
        // ********************************** //

        // Definimos una variable que vale 5
        int counter4 = 5;

        // Este ciclo nunca se ejecuta porque la condición contador3 == 10 nunca es verdadera, ya que contador3 vale 5
        while (counter4 == 10) {
            System.out.println("This loop is never executed");
        }
        
        /*
         * Otra estructura muy similar es el Do...While el cual hace lo mismo que el While con la diferencia que la condición
         * se evalúa al final y no al principio como en un While
         * Ver siguiente apunte para cubrir el Do...While
         */
    }
}
