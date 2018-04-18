package ar.com.javacuriosities.basics;

public class Lesson04RelationalOperators {

    public static void main(String[] args) {
        System.out.println("Lesson 04 - Relational Operators");

        /*
         * Java nos brinda una gran cantidad de operadores relacionales
         * los cuales se evalúan y nos entregan un valor boolean
         * boolean = Solo acepta dos valores true(Verdadero) o false(Falso)
         */

        // ********************************** //
        // Operador > y >= (Los dos operadores se deben escribir juntos >= y NO > =)
        // ********************************** //

        // Definimos una variable del tipo int y la inicializamos en 100
        int number1 = 100;

        // Primero imprimimos el valor de la variable para mostrar que es un numero
        System.out.println(number1);

        // Imprimimos el resultado de la expresión numero > 99
        // Como numero vale 100 y 100 es mayor que 99 entonces imprime true
        System.out.println(number1 > 99);

        // Imprimimos el resultado de la expresión numero >= 101
        // Como numero vale 100 y 101 no es mayor o igual que 100 imprime false
        System.out.println(number1 >= 101);

        // Imprimimos el resultado de la expresión numero > 100
        // Como numero vale 100 y 100 es igual a 100 pero no mayor ni menor entonces imprime false
        System.out.println(number1 > 100);

        // Imprimimos el resultado de la expresión numero >= 100
        // Como numero vale 100 y 100 es igual a 100 y usamos el operador >= imprimimos true
        System.out.println(number1 >= 100);

        // ********************************** //
        // Operador < y <=
        // ********************************** //

        // Definimos una variable del tipo int y la inicializamos en 100
        int number2 = 100;

        // Primero imprimimos el valor de la variable para mostrar que es un numero
        System.out.println(number2);

        // Imprimimos el resultado de la expresión numero < 99
        // Como numero vale 100 y 100 es mayor que 99 entonces imprime false
        System.out.println(number2 < 99);

        // Imprimimos el resultado de la expresión numero <= 101
        // Como numero vale 100 y 101 es mayor que 100 imprime true
        System.out.println(number2 <= 101);

        // Imprimimos el resultado de la expresión numero < 100
        // Como numero vale 100 y 100 es igual a 100 pero no mayor ni menor entonces imprime false
        System.out.println(number2 < 100);

        // Imprimimos el resultado de la expresión numero <= 100
        // Como numero vale 100 y 100 es igual a 100 y usamos el operador >= imprimimos true
        System.out.println(number2 <= 100);

        // ********************************** //
        // Operador ==
        // Para hacer comparaciones hay que usar == y para asignar valores un solo igual
        // ********************************** //

        // Defino dos variables que vamos a usar para los ejemplos
        int equalNumber = 87;
        String equalName = "Cosme";

        // Esta comparación devuelve true porque la variable equalNumber contiene el valor 87
        System.out.println(equalNumber == 87);

        // Esta comparación devuelve false porque la variable equalNumber contiene el valor 87 el cual es distinto a 99
        System.out.println(equalNumber == 99);

        // Esto imprime false porque la variable equalName es igual a "Cosme"
        System.out.println(equalName == "Homero");

        // Esto imprime true porque la variable equalName contiene el valor "Cosme"
        System.out.println(equalName == "Cosme");

        /*
         * Comentario: Si se fijan las dos lineas de arriba se marcan como Warnings,
         * porque los String no DEBEN compararse usando el operador ==, porque estos
         * son objetos, usamos el operador == porque el curso no entra en detalle con objetos
         * pero esto es algo que deben tener en cuenta, a continuación se muestra como comparar Strings
         * en Java
         */
        
        // Estas dos formas son validas para comparar Strings la segunda es mejor que la primera porque
        // reduce las chances de NPE (NullPointerExcepcion)
        System.out.println(equalName.equals("Cosme"));
        System.out.println("Cosme".equals(equalName));
        
        // ********************************** //
        // Operador !=
        // Se usa este operador para consultar sobre la desigualdad
        // ********************************** //
        
        // Defino dos variables que vamos a usar para los ejemplos
        int notEqualNumber = 87;
        String notEqualName = "Cosme";

        // Esta comparación devuelve false porque la variable notEqualNumber vale 87, por ende no es distinto
        System.out.println(notEqualNumber != 87);

        // Esta comparación devuelve true porque la variable notEqualNumber contiene el valor 87 el cual es distinto a 99
        System.out.println(notEqualNumber != 99);

        // Esto imprime true porque la variable notEqualName es igual a "Cosme" y esto es distinto a "Homero"
        System.out.println(notEqualName != "Homero");

        // Esto imprime false porque la variable notEqualName contiene el valor "Cosme" o sea son iguales
        System.out.println(notEqualName != "Cosme");
        
        /*
         * Comentario: Asi como no se debe usar == para comparar Strings tampoco se debe usar !=
         */
        
        // Estas dos formas son validas para comparar Strings la segunda es mejor que la primera porque
        // reduce las chances de NullPointerExcepcion.
        // Aca hicimos lo mismo que antes pero pusimos el operador ! para negar la expresión
        System.out.println(!equalName.equals("Cosme"));
        System.out.println(!"Cosme".equals(equalName));
    }
}
