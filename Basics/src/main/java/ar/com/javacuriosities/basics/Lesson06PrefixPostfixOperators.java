package ar.com.javacuriosities.basics;

public class Lesson06PrefixPostfixOperators {

    public static void main(String[] args) {
        System.out.println("Lesson 06 - Prefix Postfix Operators");
        // Ejemplo 1 - Diferencia entre && y & (Lo mismo aplica para || y |)
        
        // Defino dos variables del tipo int e inicializo cada una
        int number1 = 100;
        int number2 = 200;
        
        if (number1 > 150 && number2 > 150) {
            System.out.println("Both numbers are greater than 150");
        } else {
            System.out.println("At least one of the numbers is lower than 150");
        }
        
        // Mismo código que antes pero cambiando el && por &
        if (number1 > 150 & number2 > 150) {
            System.out.println("Both numbers are greater than 150");
        } else {
            System.out.println("At least one of the numbers is lower than 150");
        }
        
        /*
         * Si miramos el ejemplo de arriba vamos a ver que son idénticos salvo que uno tiene &&
         * y el otro un solo &.
         * 
         * En el primer ejemplo vemos que number1 vale 100 y la primer condición pregunta
         * SI number1 > 150 Y number2 > 150
         * en la primer parte ya vemos que number1 NO ES mayor que 150 entonces al usar && la computadora
         * ya evalúa esa sentencia como FALSA
         * En cambio cuando usamos & la computadora evalúa las dos sentencias y luego calcula el valor
         * de verdad del IF
         */
        
        // Ejemplo 2 - Diferencia entre ++contador Y contador++
        
        int counter1 = 1;
        
        // Uso del counter1++
        // Fíjense que este ciclo imprime los números del 1 al 10
        while (counter1 <=10) {
            System.out.println(counter1++);
        }
        
        int counter2 = 1;
        
        // Uso del ++counter2
        // Fíjense que este ciclo imprime los números del 2 al 11
        while (counter2 <=10) {
            System.out.println(++counter2);
        }
        
        /*
         * La diferencia es la siguiente
         * 
         * counter1++ = Primero imprime el numero y luego incrementa el valor de la variable.
         * ++counter2 = Primero incrementa eel valor de la variable y luego imprime su valor.
         */     
    }
}
