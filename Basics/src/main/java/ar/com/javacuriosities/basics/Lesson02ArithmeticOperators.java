package ar.com.javacuriosities.basics;

public class Lesson02ArithmeticOperators {

    public static void main(String[] args) {
        System.out.println("Lesson 02 - Arithmetic Operators");

        /*
         * Los operadores nos proveen la capacidad de realizar operaciones matemáticas o lógicas
         * en base a lo que deseamos hacer vamos a ir usando los distintos operadores provistos
         * por el lenguaje.
         */
        
        // Defino dos variables del tipo entero, donde una contiene el valor 15 y la otra el valor 10.
        int number1 = 15;
        int number2 = 10;
        
        // Uso el operador suma y muestro el result por consola
        System.out.println(number1 + number2);
        
        // Uso el operador resta y muestro el result por consola
        System.out.println(number1 - number2);
        
        // Uso el operador multiplicación * muestro el result por consola
        System.out.println(number1 + number2);
        
        /*
         * Uso el operador division y muestro el result por consola
         * Una cosa importante para notar aquí es que 15 / 10 = 1,5
         * Pero de esta forma nos imprime el valor 1
         * Esto se debe a que estamos usando números enteros, si quisiéramos
         * obtener el number con coma alguna de las dos variables debería ser del tipo double
         */        
        System.out.println(number1 / number2);
        
        // Uso el operador resto y muestro el result por consola
        System.out.println(number1 % number2);
        
        // ********************************** //
        // Variables auxiliares y operadores
        // ********************************** //
        
        // Aquí definimos una variable llamada result
        int result = 0;
        
        // Imprimo el valor de la variable result
        System.out.println("Result 1: " + result);
        
        // Fíjense que aca realizo la suma de number1 + number2 y el total de eso
        // lo asigno a la variable llamada result, como se puede ver no se vuelve
        // a poner la palabra int porque en la linea anterior se definió la variable
        // y en esta se le asigna un valor.
        result = number1 + number2;
        
        // Vuelvo a imprimir valor de la variable result
        // ahora no va a imprimir 0 porque en la linea anterior se le asigno un nuevo valor
        // el number impreso es igual a number1 + number2
        System.out.println("Result 2: " + result);
        
        // ********************************** //
        // Operadores asignación
        // ********************************** //
        
        // Defino dos variables y una es iniciada en 10 y la otra en 20
        int sumAccumulator = 10;
        int subtractionAccumulator = 20;
        
        // Imprimo el valor de las variables
        System.out.println("Sum Accumulator: " + sumAccumulator);
        System.out.println("Subtraction Accumulator: " + subtractionAccumulator);
        
        // Uso el operador += que sirve para sumar y asignar en la misma instruccion.
        sumAccumulator += 50;
        
        // Uso el operador -= que sirve para restar y asignar en la misma instruccion
        subtractionAccumulator -= 15;
        
        // Imprimo nuevamente el valor de las variables
        // Aqui se ve que el valor de sumAccumulator se incremento en 50 y el valor de subtractionAccumulator se redujo en 15
        System.out.println("Sum Accumulator: " + sumAccumulator);
        System.out.println("Subtraction Accumulator: " + subtractionAccumulator);
        
        
        // ********************************** //
        // Operadores de incremento y decremento
        // ********************************** //
        
        // Aca definimos dos variables para usar en el ejemplo fíjense sus valores
        int increment = 9;
        int decrement = 7;
        
        // Imprimo el valor de las variables
        System.out.println("Increment: " + increment);
        System.out.println("Decrement: " + decrement);
        
        // En esta linea el valor se incrementa en 1 cada vez que usamos ++ se suma 1
        increment++;
        
        // En esta linea el valor se decrementa en 1 cada vez que usamos -- se resta 1        
        decrement--;
        
        // Vuelvo a imprimir valor
        System.out.println("Increment: " + increment);
        System.out.println("Decrement: " + decrement);
        
        /*
         * Debemos diferenciar dos formas de usar este operador
         * ++increment = Primero hace el incremento y después continua evaluando
         * increment++ = Primero evalúa y después hace el incremento
         */
        
        int operatorExample = 5;
        
        // Aca imprime el valor 5 porque su valor guardado es 5
        System.out.println("Operator Example Value : " + operatorExample);
        
        // Aca sigue imprimiendo el valor 5 pero después de imprimir incrementa el valor
        System.out.println("Operator Example Value : " + operatorExample++);
        
        // Si imprimimos aca vemos que esta en 6
        System.out.println("Operator Example Value : " + operatorExample);
        
        // Aca hace al revés primero incrementa y luego imprime osea imprime 7
        System.out.println("Operator Example Value : " + ++operatorExample);
        
        
        // ********************************** //
        // Operadores de incremento y decremento (Cuidado !!!)
        // ********************************** //
        int number = 10;
        
        // Al hacer la igualación y usar el operador de pos incremento este incremento no se realiza
        number = number++;
        System.out.println("Case 1 - Number value: " + number);
        
        // Aqui primero incrementamos luego asignamos
        number = ++number;
        System.out.println("Case 2 - Number value: " + number);
        
        // Este código genera un ciclo infinito porque el valor de la variable number nunca cambia
        /*
        while (number < 20) {
            System.out.println("Infinite Loop");
            number = number++;
        }
        */
        
         // ********************************** //
        // += vs =+ Y -= vs =-
        // ********************************** //
        int var1 = 10;
        int var2 = 10;
        
        // Agarra el valor de var1 y le suma 15 (Resultado 25)
        var1 += 15;
        
        // Agarra la var1 y le asigna el valor 15 positivo (Resultado 15)
        var1 =+ 15;
        
        // Agarra el valor de var2 y le resta 5 (Resultado 5)
        var2 -= 5;
        
        // Agarra la var2 y le asigna el valor 5 negativo (Resultado -5)
        var2 =- 5;
    }
}
