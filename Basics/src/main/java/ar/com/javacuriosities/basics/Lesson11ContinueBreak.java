package ar.com.javacuriosities.basics;

public class Lesson11ContinueBreak {

    public static void main(String[] args) {
        System.out.println("Lesson 11 - Continue And Break");

        /*
         * El estas dos palabras reservadas se suelen utilizar dentro de
         * bucles , como también vimos antes el break también se usa en bifurcaciones como
         * el switch.
         * continue = Vuelve al comienzo del ciclo en el cual esta contenido
         * break = Corta el ciclo en el cual esta contenido
         */

        // ********************************** //
        // Diferencias entre Continue and Break
        // ********************************** //

        /*
         * Realizamos un bucle de 0 hasta 9, en teoría no tiene nada raro
         * aunque si se fijan podrán ver que dentro del ciclo hay una linea comentada
         * si removemos el comentario la compilación falla y muestra el mensaje de error
         * "unreachable statement", esto quiero decir que ese código nunca se va a ejecutar,
         * eso sucede porque cuando el programa encuentra la sentencia continue vuelve a ir al principio
         * del bucle como estamos en un FOR la variable contador se incrementa sola, no sucedería lo mismo en un WHILE
         */
        for (int counter = 0; counter < 10; counter++) {
            System.out.println("1- Inside for: " + counter);
            continue; // Deja de ejecutar el ciclo y va a la siguiente ejecución
            // System.out.println("It would never written in the output");
        }

        for (int counter = 0; counter < 10; counter++) {
            System.out.println("2- Inside for: " + counter);
            break; // Corta el ciclo totalmente y ejecuta el código que sigue
            // System.out.println("It would never written in the output");
        }
        System.out.println("After second for");

        // ********************************** //
        // Ejemplos Continue
        // ********************************** //

        // Definimos una variable del tipo int y la inicializamos en 0
        int counterUsingContinue = 0;

        // Hacemos un ciclo de 1 hasta 10, porque fíjense que el contador inicia en 0 pero
        // la primer linea dentro del while le incrementa el valor
        while (counterUsingContinue < 10) {
            counterUsingContinue++; // Si comentan esta linea y removemos el ultimo comentario que incrementa el contador tienen un ciclo infinito porque se incrementaría hasta 5 pero nunca mas su valor
            if (counterUsingContinue == 5) {
                System.out.println("Line 5 Continue - It does not print the counter message");
                continue; // Vuelve a empezar el ciclo, dependiendo donde este el incremento podes enfrentar un ciclo infinito o no
            }

            System.out.println("Continue - Counter value: " + counterUsingContinue); // Fíjense que contador no imprimió su valor al valer 5 porque esta linea no se ejecuto

            //counterUsingContinue++; // Si comentan la primer linea del ciclo y descomentan la ultima que incrementa el contador tienen un ciclo infinito porque se incrementaría hasta 5 pero nunca mas su valor
        }

        // ********************************** //
        // Ejemplos Break
        // ********************************** //

        // Definimos una variable del tipo int y la inicializamos en 0
        int counterUsingBreak = 0;

        // Hacemos un ciclo de 0 hasta 9
        while (counterUsingBreak < 10) {
            if (counterUsingBreak == 5) {
                System.out.println("Line 5 Break - It does not print the counter message");
                break; // Al llegar al valor 5 se corta el ciclo, fíjense que solo imprime los valores hasta el 4
            }

            System.out.println("Break - Counter value: " + counterUsingBreak);
            counterUsingBreak++;
        }


        // ********************************** //
        // Continue and Break (Labeled)
        // Esto no es parte del curso y la verdad que nadie lo usa pero es una utilidad provista por el lenguaje
        // asi que les dejo dos ejemplos
        // ********************************** //

        breakLabel:
        for (int i = 0; i < 10; i++) {
            System.out.println("Loop i. i = " + i);
            for (int j = 0; j < 10; j++) {
                System.out.println("Loop j. j = " + j);
                for (int k = 0; k < 10; k++) {
                    System.out.println("Loop k. k = " + k);
                    break breakLabel; // Esta instrucción corta el label que mencionamos aca
                }
            }
        }

        continueLabel:
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.println("Inside");
                continue continueLabel; // Esta instrucción manda la ejecución a donde esta el label que mencionamos
            }
            System.out.println("Outside"); // Nunca se escribe
        }
        System.out.println("Outside loop");
    }
}
