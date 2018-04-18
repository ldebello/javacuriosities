package ar.com.javacuriosities.basics;

public class Lesson01Variables {

    public static void main(String[] args) {
        System.out.println("Lesson 01 - Variables");

        // El comando System.out.println sirve para imprimir por pantalla
        System.out.println("Hello World");

        // ********************************** //
        // Explicación variables
        // ********************************** //
        /* Las variables son contenedores de datos y estas se encuentran tipadas o
         * sea cada variable tiene un tipo de dato.
         *
         * Tipos de datos (Respetar mayúsculas y minúsculas):
         * 
         * int = variable del tipo numérica que permite almacenar números enteros.
         * byte = variable del tipo numérica que permite almacenar números enteros.
         * short = variable del tipo numérica que permite almacenar números enteros.
         * long = variable del tipo numérica que permite almacenar números enteros.
         * 
         * La diferencia entre estos 4 tipos es que dependiendo del tipo vamos a poder
         * almacenar números mas grandes o mas chicos, por ejemplo el byte como maximo
         * puede almacenar el número 127
         * 
         * float = variable del tipo numérica que permite almacenar números decimales
         * double = variable del tipo numérica que permite almacenar números decimales
         * 
         * Nuevamente aquí la diferencia es en base al rango de numero que permiten grabar
         * 
         * Además tenemos variables que representan caracteres o sucesión de caracteres
         * 
         * char = representa un carácter y uno solo, ejemplo 'A'
         * String = representa una sucesión de caracteres, ejemplo "Hola Mundo"
         * 
         * Todos los tipos de datos que empiezan en minúscula int, byte, short, long
         * float, double, char son tipos primitivos.
         * 
         * Los que empiezan con mayúsculas no son cubiertos en esta intro pero son Objetos
         * /
         
         // ********************************** //
         // Algunos ejemplos de definir variables e imprimirlas por consola
         // ********************************** //
                
         /*
         * Aca estamos definiendo una variable que se llama age y que su
         * tipo de datos es int(Recuerden que los int son numero enteros)
         * y además la inicializo con el valor 27.
         */
        int age = 27;

        // Aca imprimo por pantalla el valor de la variable age.
        System.out.println(age);

        /*
         * La diferencia entre esta linea y la anterior es:
         * En la anterior estoy imprimiendo el valor de una variable
         * En esta linea estoy imprimiendo una palabra en concreto.
         * Fíjense que la diferencia se da porque abajo use comilla dobles
         * para encerrar la palabra y por eso el programa la imprime por pantalla
         */
        System.out.println("age");

        /*
         * Ahora voy a definir otra variable pero le voy a cambiar el tipo de dato
         * asi vemos que hay varios tipos.
         * byte, short, int, long (Son números enteros)
         * float, double (Son números decimales)
         */

        // Aca defino una variable que se llama promedio y la defino de tipo numero decimal.
        double average = 6.5;

        // Usando esta linea imprimo el valor de la variable llamada promedio.
        System.out.println(average);


        /*
         * Como podemos ver arriba ya definimos dos variables y cada una tiene un tipo de dato
         * distinto. La idea de las variables es que son como contenedores que guardan 
         * informacion en la memoria RAM de la computadora.
         */

        /*
         * Bueno ahora vamos a definir una variable del tipo cadena de caracteres, o sea
         * con esta variable nosotros podemos guardar frases o textos.
         */

        // Esto define una variable que contiene la frase que esta entre comilla dobles.
        String initialPhrase = "Java Basic Syntax";

        // Aca imprimo el valor que tiene la variable llamada fraseInicial.
        System.out.println(initialPhrase);

        // ********************************** //
        // Algunos ejemplos de definir varias variables del mismo tipo
        // ********************************** //

        /*
         * Aqui estamos definiendo dos variables del tipo int que aun no fueron inicializadas.
         */
        int var1, var2;

        /*
         * Aqui definimos dos variales y en la misma linea inicializamos una y ya en la segunda linea
         * asignamos un valor a la segunda.
         */
        int var3 = 1, var4;
        var4 = 10;

        /*
         * Aqui definimos dos variables, iniciliazamos la primera y la segunda es inicializada
         * con el valor de la segunda.
         */
        int var5 = 1, var6 = var5;


        // ********************************** //
        // Ejemplos definir Constantes
        // ********************************** //

        /*
         * En esta linea estamos definiendo una constante.
         * Una constante es una variable la cual se inicializa en un valor y ese valor
         * no cambia nunca durante la ejecución del programa.
         * Por convención los nombres de las constantes se escriben en mayúscula
         * además podemos notar la palabra final, esta palabra le va a decir a Java
         * que esta variable no puede cambiar de valor
         */
        final double PI = 3.141592;

        System.out.println("PI Constant value: " + PI);


        // ********************************** //
        // Ejemplo rango de variables
        // ********************************** //

        /*
         * Definimos una variable llamada maxAge, y la inicializamos en 127
         * esto compila sin problema pero si intentamos asignarle el valor 128
         * veremos un error en nuestro código, nos dejara compilar el código
         * osea va a generar el .class pero al ejecutar va a arrojar error en tiempo
         * de ejecución.
         * En base al tipo de dato vamos a tener diferentes rango con extremos mínimos y máximos
         */
        byte maxAge = 127;


        // ********************************** //
        // Ejemplos boolean
        // ********************************** //

        /*
         * Definimos la variable del tipo boolean y solo puede contener true o false
         * en Java no es valido comparar contra int a no ser que hagamos al conversion
         * del boolean a su tipo correspondiente.
         * Ejemplo PHP: if (true > 0) En java no es permitido hacer esto en esta forma
         * debemos usar true o false o hacer una conversion antes
         */
        boolean isHigh = true;
        
        // Si escribimos de esta manera el IF se asume que la pregunta es si esAlto == true
        if (isHigh) {
            System.out.println("Inside IF block");
        }
    }
}
