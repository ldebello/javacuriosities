package ar.com.javacuriosities.basics;

public class Lesson12ForStructure {

    public static void main(String[] args) {
        System.out.println("Lesson 12 - For Structure");

        /*
         * Esta estructura sirve para definir una condición inicial y ejecutar cierto código hasta que llega a un estado
         * final y en cada iteración ejecuta una acción
         */

        // ********************************** //
        // Ciclo FOR de 0 a 10
        // ********************************** //
        for (int counter = 0; counter <= 10; counter++) {
            System.out.println("Counter: " + counter);
        }

        // Esta linea es invalida porque la variable contador fue definida dentro del FOR y por ende
        // su ámbito de variable es solo dentro del ciclo, si quisiéramos hacer esto debemos definir la variable
        // antes del for
        // System.out.println("Counter: " + counter);

        // ********************************** //
        // Variantes ciclo FOR
        // ********************************** //

        // Ciclo FOR incrementando valor de contador
        for (int counter = 1; counter <= 10; counter++) {
            System.out.println("Incrementing counter: " + counter);
        }

        // Ciclo FOR decrementando valor de contador
        for (int counter = 10; counter >= 1; counter--) {
            System.out.println("Decrementing counter: " + counter);
        }

        // Ciclo FOR infinito, como se puede ver la condicion pide que contador sea mayor a 1 pero como siempre incrementamos el valor esto se va a cumplir siempre
        /*
        for (int counter = 1; counter >= 1; counter++) {
            System.out.println("Counter: " + counter);
        }
        */
        
        // Ciclo FOR con dos variables
        for (int firstCounter = 1, secondCounter = 2; firstCounter <= 10; firstCounter++, secondCounter *= 2) {
            System.out.println("Second counter value: " + secondCounter);
        }        
        
        // Ciclo FOR anidado
        // Se realiza un ciclo de 1 hasta 10 pero por cada iteración se recorren todas las letras del alfabeto
        for (int contador = 1; contador <= 10; contador++) {
            System.out.println("El numero es: " + contador); // Imprime el valor del numero
            // Por cada numero impreso vamos a imprimir el abecedario letra por letra, esto es posible porque cuando le decimos que inicie en 'a' y termine en 'z'
            // en realidad le estamos diciendo que convierte esas letras a código ASCII y haga el ciclo entre esos dos valores
            // Letra 'a' = 97 - Letra 'z' = 122 (Mirar tabla ASCII del resumen clase 3)
            for (char letra = 'a'; letra <= 'z'; letra++) {
                System.out.print(letra);
                System.out.print("-");
            }
            System.out.println(""); // Imprimo un salto de linea
        }
    }
}
