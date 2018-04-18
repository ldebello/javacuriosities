package ar.com.javacuriosities.basics;

public class Lesson07IfStructure {

    public static void main(String[] args) {
        System.out.println("Lesson 07 - If Structure");

        /*
         * La estructura IF nos deja controlar el flujo de nuestro programa usando
         * expresiones booleanas las cuales van a afectar la lógica del programa
         */

        // Definimos una variables que contiene la age de una persona
        int age = 27;

        // Ejecutamos un IF que pregunta si la age es MAYOR que 27, si esto fuera cierto
        // entonces ejecutaria el código que pertenece al IF en este caso el codigo entre { ... }
        if (age > 27) {
            System.out.println("Age is greater than 27");
        }

        /*
         * El IF de arriba esta perfecto ahora veamos varias formas de escribir el mismo IF
         * Yo recomiendo la forma de arriba que es la recomendada por Sun/Oracle.
         */

        // Forma 1: La original que escribimos arriba
        if (age > 27) {
            System.out.println("Age is greater than 27");
        }

        // Forma 2: Eliminamos las { ... } porque como es un IF de una linea no necesita llaves
        // Solamente puedo usar estructuras sin llave cuando solo contienen una linea
        if (age > 27) {
            System.out.println("Age is greater than 27");
        }

        // Forma 3: Con las llaves en el renglón de abajo, muchos programadores de .NET usan
        // esta forma porque en esa plataforma se escribe asi, es solo una forma de escribirlo no cambia
        // su comportamiento para nada
        if (age > 27) {
            System.out.println("Age is greater than 27");
        }

        /*
         * Como vimos antes los IF anteriores no imprimen nada porque su condición no es valida
         * age > 27, la age vale 27 entonces si quiero que entre al IF la age tiene que ser mayor en este momento es igual
         * Aqui puedo hacer dos cosas o cambiar el valor de la variable age o usar otro operador por ejemplo, >=
         */

        if (age >= 27) {
            System.out.println("Age is greater than 27");
        }

        /*
         * Un error típico con el IF es el punto y coma, fíjense que la estructura en si no usa ; sino que las sentencias
         * dentro del IF la usan, comparen esto dos ejemplos
         */

        if (age > 27) {
            System.out.println("1- Age is equal to 27, so this message should not be printed");
        }

        // Comparen el IF de arriba con este y vean que este tiene un ; antes de la { de apertura, entonces Java
        // asume que ese IF empieza y termina ahi y la siguiente linea la ejecuta sin ninguna condición, por eso imprime el mensaje
        if (age > 27);
        {
            System.out.println("2- Age is equal to 27, so this message should not be printed");
        }

        /*
         * Veamos también que el IF puede tener mas de una sola linea, en este caso no puedo remover las { ... ,
         * sino cambia la lógica del programa
         */
        if (age >= 27) {
            System.out.println("If Block with several statements:");
            System.out.println("Statement 1");
            System.out.println("Statement 2");
            System.out.println("Statement 3");
        }

        /*
         * Los ejemplos de arriba cubren los casos de un IF simple donde hay una sola condición, podemos agregar mas condiciones
         * usando los operadores lógicos vistos en el apunte anterior.
         */

        // Defino una variable boolean que dice si soy alto o no y la inicio con valor true
        boolean isHigh = true;

        // Es un IF con dos condiciones las cuales tiene un operador && entonces las dos deben cumplirse
        // para que el IF sea valido

        // Forma 1: Escribo que la variable isHigh es igual a true
        if (age > 26 && isHigh == true) {
            System.out.println("1- He is older than 26 years old and tall");
        }

        // Forma 2: No defino nada sobre la variable isHigh pero es igual que decir isHigh == true
        if (age > 26 && isHigh) {
            System.out.println("2- He is older than 26 years old and tall");
        }

        // Forma 3: Puedo comparar la variable isHigh contra false
        if (age > 26 && isHigh == false) {
            System.out.println("3- He is older than 26 years old and not tall");
        }

        // Forma 4: Puedo negar la variable isHigh y en este caso seria igual a isHigh == false
        if (age > 26 && !isHigh) {
            System.out.println("4- He is older than 26 years old and not tall");
        }

        // ********************************** //
        // Estructura IF y ELSE
        // Aqui aplican los mismo conceptos sobre las llaves pero para la parte del ELSE siempre y cuando ocupe una sola linea
        // Recordar que el ELSE es opcional, puedo escribir IF sin usar una clausula ELSE al final
        // ********************************** //

        // Este ejemplo muestra que existe una parte ELSE para los IF, si leemos esta sentencia seria de la siguiente forma
        // SI (age es mayor que 27) ENTONCES imprimo el mensaje del if SINO imprimo el mensaje del else
        // En este caso la age es igual a 27 entonces el código va por el lado ELSE
        if (age > 27) {
            System.out.println("IF/ELSE- Age is greater than 27 so IF branch");
        } else {
            System.out.println("IF/ELSE- Age is not greater than 27 so ELSE branch");
        }

        // ********************************** //
        // Estructura IF y ELSE IF y ELSE
        // Aqui aplican los mismo conceptos sobre las llaves pero para la parte del ELSE siempre y cuando ocupe una sola linea
        // Recordar que el ELSE es opcional, puedo escribir IF sin usar una clausula ELSE al final
        // ********************************** //

        // Esto es lo que se conoce como ELSE IF, la idea es que Java va condición por condición hasta que lograr entrar en una
        // SI (age es igual a 18) ENTONCES imprimo A.
        // SINO SI (age es igual a 19) ENTONCES imprimo B.
        // SINO imprimo C.
        if (age == 18) {
            System.out.println("A. IF branch");
        } else if (age == 19) {
            System.out.println("B. ELSE IF branch");
        } else {
            System.out.println("C. ELSE branch");
        }

        /*
         * Es super importante y recomendable que usen llaves en estos tipos de IF porque es mas fácil de visualizar el código
         * y recuerden que si abro una llave debe tener su correspondiente llave de cierre
         */
        
        // ********************************** //
        // IF Anidados
        // Los IF anidados no son mas que IF dentro de otros IF, se puede tener tanto como nosotros deseemos
        // ********************************** //
        if (age > 26) {
            if (isHigh) {
                System.out.println("Tall and Old");
            } else {
                System.out.println("No Tall and Old");
            }
        }
        
        // ********************************** //
        // IF con operador = (Cuidado!!!!)
        // ********************************** //
        
        // Imprimimos el valor de la variable isHigh
        System.out.println("Variable isHigh is: " + isHigh);
        
        // Aca en realidad estamos asignando el valor false a la variable isHigh
        if (isHigh = false) {
            System.out.println("This text will be not printed");
        }        
    }
}
