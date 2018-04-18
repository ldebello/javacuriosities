package ar.com.javacuriosities.basics;

public class Lesson05LogicalOperators {

    public static void main(String[] args) {
        System.out.println("Lesson 05 - Logical Operators");

        /*
         * Los operadores lógicos devuelven valores de verdad 0 o 1
         * Verdadero o Falso.
         */
        
        // Definimos dos variables la cual una es true y la otra false
        boolean var1 = true;
        boolean var2 = false;
        
        // Esto va a imprimir true porque la variable var1 vale true
        System.out.println(var1);
        
        // Esto va a imprimir false porque la variable var2 vale false
        System.out.println(var2);
        
        // ********************************** //
        // Operador !
        // Este operador sirve para negar o invertir el valor de verdad ofrecido
        // ********************************** //
        
        // Esto va a imprimir false porque la variable var1 vale true y uso el operador ! para cambiar su valor
        System.out.println(!var1);
        
        // Esto va a imprimir true porque la variable var2 vale false y uso el operador ! para cambiar su valor
        System.out.println(!var2);
        
        // Debemos tener cuidado si negamos dos veces porque obtendríamos el valor original
        // O sea, inicia en true --> pasa a false (Porque lo negamos una vez) --> Vuelve a true (Porque lo volvimos a negar)
        System.out.println(!!var1);
        
        /*
         * Como vimos hay dos valores boolean true o false, ahora
         * podemos empezar a sumar operadores para analizar estas variables en conjunto,
         * primero vimos el operador ! que solo actúa sobre una variable pero tenemos operadores
         * que reciben mas de una operando como los mencionados a continuación
         */
        
         // Definimos tres valores de verdad para ir viendo sus combinaciones
        boolean condition1 = true;
        boolean condition2 = false;
        boolean condition3 = true;
        
        // ********************************** //
        // Operador && (AND)
        // Este es el operador && el cual indica si dos operando son verdaderos
        //
        // Tabla de verdad
        // true && true = true
        // true && false = false 
        // false && true = false
        // false && false = false
        // ********************************** //
        
        // Aca imprimimos el valor de verdad devuelvo por el operador AND
        // Fijense que el operando uno vale true y el operando dos vale false
        // Entonces devuelve false
        System.out.println(condition1 && condition2);
              
        // Fijense que el operando uno vale true y el operando dos vale true
        // Entonces devuelve true
        System.out.println(condition1 && condition3);
        
        // Este ejemplo imprime false porque es igual que el primero pero solo
        // con los operando cambiados de lugar, igual debemos estar atentos a un pequeño
        // detalle en este caso Java primero evalúa la primer expresión la cual vale false
        // entonces detecta que usamos un operador && y como sabe que si una ya da false
        // no sigue evaluando la segunda
        System.out.println(condition2 && condition1);
        
        // ********************************** //
        // Operador || (OR)
        // Este es el operador || el cual indica si alguno de los dos operando es true
        //
        // Tabla de verdad
        // true || true = true
        // true || false = true 
        // false || true = true
        // false || false = false
        // ********************************** //
        
        // Aca imprimimos el valor de verdad devuelvo por el operador OR
        // Fijense que el operando uno vale false y el operando dos vale true
        // Entonces devuelve true
        System.out.println(condition2 || condition1);
              
        // Fijense que el operando uno vale true y el operando dos vale true
        // Igual devuelve true porque al menos necesita uno
        System.out.println(condition1 || condition3);
        
        // Este ejemplo imprime true porque es igual que el primero pero solo
        // con los operando cambiados de lugar, igual debemos estar atentos a un pequeño
        // detalle en este caso Java primero evalúa la primer expresión la cual vale true
        // entonces detecta que usamos un operador || y como sabe que si una ya da true
        // no continua evaluando la expresión y retorna true
        System.out.println(condition1 || condition2);
        
        // ********************************** //
        // Operadores & (AND Binario) y  | (OR Binario)
        // Estos operadores tiene la misma tabla de verdad que los anteriores ,pero con
        // la diferencia que los anteriores se conocen como operadores de circuito corto
        // porque apenas detectan que pueden devolver un valor lo devuelven, estos
        // evaluan todos los operandos hasta el final
        // ********************************** //
        
        // Aca el operando uno es true, si usáramos || ya evalúa la primer parte y devuelve true
        // porque el OR necesita al menos uno que sea true, pero este operando va a evaluar el operando dos
        // el cual es var2 y después va terminar devolviendo var1 porque true | false = true
        System.out.println(condition1 | condition2);
        
        // ********************************** //
        // Operador !
        // Sirve para negar la expresión
        //
        // Tabla de verdad
        // !true = false
        // !false = true
        // ********************************** //
        
        // En este caso condition1 vale true, pero al ser negado imprime false
        System.out.println(!condition1);
        
        // En este caso condition2 vale false, pero al ser negado imprime true
        System.out.println(!condition2);
        
        // ********************************** //
        // Operador ^ (OR Exclusivo)
        // Este es el operador ^ el cual indica que una expresión es true pero la otra es falsa
        //
        // Tabla de verdad
        // true ^ true = false
        // true ^ false = true 
        // false ^ true = true
        // false ^ false = false
        // ********************************** //
        
        // Aca imprimimos el valor de verdad devuelvo por el operador OR Exclusivo
        // Fíjense que el operando uno vale true y el operando dos vale false
        // Entonces devuelve true
        System.out.println(condition1 ^ condition2);
              
        // Fíjense que el operando uno vale true y el operando dos vale true
        // Por definición nos devuelve false porque nosotros queremos que uno solo sea true
        System.out.println(condition1 ^ condition3);
    }
}
