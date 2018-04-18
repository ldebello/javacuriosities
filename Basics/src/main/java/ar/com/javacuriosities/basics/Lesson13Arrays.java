package ar.com.javacuriosities.basics;

import java.util.Arrays;

public class Lesson13Arrays {

    public static void main(String[] args) {
        System.out.println("Lesson 13 - Arrays");

        /*
         * Los arrays o vectores son estructuras de datos ofrecidas por Java para manejar un conjunto de datos que pertenecen al mismo tipo, o sea
         * podríamos imaginar los vectores como los vagones de un tren donde cada vagón puede guardar un dato.
         * 
         * Ejemplo (Tren con 5 vagones - Vector con 5 posiciones)
         * Tren:
         * Vagon0 - Vagon1 - Vagon2 - Vagon3 - Vagon4
         * Vector:
         * Posicion0 - Posicion1 - Posicion2- Posicion3 - Posicion4
         * 
         * Cada uno de estos lugares pueden guardar un dato en base al tipo de vector que usemos, o sea un vector de int puede guardar números enteros, un vector de String puede guardar
         * cadenas de textos y asi con los distintos tipos de datos.
         */

        // ********************************** //
        // Creación de arrays
        // ********************************** //

        // Forma 1
        // Creamos una variable del tipo array pero no le dijimos cuantas posiciones tiene ni que valores
        int[] arrayNumbers1;

        // Aca se inicializa el array con 10 posiciones, de esta forma todas las posiciones se inicializan en 0
        arrayNumbers1 = new int[10];

        // Tambien podemos hacer todo en una sola linea
        int[] arrayNumbers2 = new int[10];

        // Forma 2 ( La diferencia con la Forma 1 es que los corchetes están después del nombre de la variable, el efecto es el mismo)
        // Creamos una variable del tipo array pero no le dijimos cuantas posiciones tiene ni que valores
        int arrayNumbers3[];

        // Aca se inicializa el array con 10 posiciones, de esta forma todas las posiciones se inicializan en 0
        arrayNumbers3 = new int[10];

        // También podemos hacer todo en una sola linea
        int arrayNumbers4[] = new int[10];


        // Forma 3
        // Con esta forma inicializamos el vector en la misma linea, o sea definimos cuantos items tiene y cual es el valor de cada item
        int[] arrayNumbers5 = {0, 1, 1, 2, 3, 5};

        // ********************************** //
        // Acceder a sus valores
        // ********************************** //

        // Aquí definimos un array de números enteros y lo inicializamos para contener 5 números
        // La posición 0 contiene el numero 10
        // La posición 1 contiene el numero 4
        // La posición 2 contiene el numero 8
        // La posición 3 contiene el numero 6
        // La posición 4 contiene el numero 2
        int[] arrayIntNumbers = {10, 4, 8, 6, 2};

        // En esta linea estoy accediendo a la posición 3 del vector, o sea me va a imprimir el numero 6, es IMPORTANTE recordar que las posiciones empiezan desde 0
        // De esta forma podemos acceder a cualquier posición que nosotros deseamos
        System.out.println("Position: " + 3 + ", Value: " + arrayIntNumbers[3]);

        // Si ustedes descomentan esta linea veran un error que dice java.lang.ArrayIndexOutOfBoundsException: 10
        // Esto quiere decir que estan intentando acceder a la posición 10 y esta no existe, hay que tener cuidado con esto
        // System.out.println("Position: " + 10 + ", Value: " + arrayIntNumbers[10]);


        // ********************************** //
        // Funciones Java para Arrays
        // ********************************** //
        // Si intentamos imprimir el valor de la variable nos aparecerán unos símbolos raros, esto es porque en realidad un vector es un Objeto
        // Si nosotros queremos imprimir sus valores podemos usar la función
        System.out.println(arrayIntNumbers);

        // Esto nos imprime los valores del array
        // Cuando hacemos esto es necesario importar la librería import java.util.Arrays; esto va entre el package y el principio que dice public class
        System.out.println(Arrays.toString(arrayIntNumbers));

        // Si queremos saber cuanto items tiene un vector usamos el atributo length
        System.out.println("Array length: " + arrayIntNumbers.length);

        // Si queremos saber la máxima posicion para ese vector al length le restamos 1
        System.out.println("Max position is: " + (arrayIntNumbers.length - 1));

        // Ahora queremos tener otro vector con los mismos valores, entonces podemos usar la función para copiar vector
        // Defino un vector copia para llenarlo de valores, y le digo que su length es la misma que el original
        int[] arrayIntNumbersCopy1 = new int[arrayIntNumbers.length];

        // Aca usamos la función System.arraycopy para copiar desde arrayIntNumbers a arrayIntNumbersCopy1
        // Parámetro 1: Vector que quiero copiar
        // Parámetro 2: Posición desde la cual quiero copiar
        // Parámetro 3: Destino donde voy a copiar
        // Parámetro 4: Posición inicial en el nuevo vector
        // Parámetro 5: Cuanto items quiero copiar
        System.arraycopy(arrayIntNumbers, 0, arrayIntNumbersCopy1, 0, arrayIntNumbers.length);

        // Si imprimo los dos vectores veo que son iguales        
        System.out.println("1-Original: " + Arrays.toString(arrayIntNumbers));
        System.out.println("1-Copy: " + Arrays.toString(arrayIntNumbersCopy1));

        // Si quiero comparar dos vectores puedo usar la funcion
        System.out.println("Comparison 1 - ¿Are they equals? " + Arrays.equals(arrayIntNumbers, arrayIntNumbersCopy1));

        // Ahora voy a ordenar el vector que es copia para luego imprimir el valor de los dos y ver que ahora la comparación da distinto
        // Ordeno un vector con la función Arrays.sort
        Arrays.sort(arrayIntNumbersCopy1);

        // Imprimo para ver como quedo
        System.out.println("1-Original: " + Arrays.toString(arrayIntNumbers));
        System.out.println("1-Copy: " + Arrays.toString(arrayIntNumbersCopy1));

        // Vuelvo a comparar si son iguales, ahora la comparación da false
        System.out.println("Comparison 2 - ¿Are they equals? " + Arrays.equals(arrayIntNumbers, arrayIntNumbersCopy1));

        // ********************************** //
        // Recorrer arrays
        // ********************************** //

        // Para recorrer un arrays podemos usar la estrutura FOR o While dependiendo el caso, se aconseja usar FOR si se va a recorrer todo el vector

        // Creamos un FOR que va desde 0 hasta ser menor que el total de items y en cada iteración incremento la posición en 1
        for (int position = 0; position < arrayIntNumbers.length; position++) {
            System.out.println("For Iteration - Position: " + position + ", Value: " + arrayIntNumbers[position]);
        }

        // Ejemplo para recorrer con While
        // Definimos una variable que tiene la posición inicial
        int currentPosition = 0;

        // La condición de corte es la misma que usamos en el FOR de arriba
        while (currentPosition < arrayIntNumbers.length) {
            // Imprimos por pantalla la posición y su valor
            System.out.println("While Iteration - Position: " + currentPosition + ", Value: " + arrayIntNumbers[currentPosition]);
            currentPosition++; // Incrementamos la posición
        }

        // ********************************** //
        // Inicializar un vector manualmente
        // ********************************** //
        // Aca solo definimos un vector de 10 posiciones donde cada posición vale 0 porque es el valor por default
        int[] twoValues = new int[10];

        // Imprimo sus valores antes de inicializar
        System.out.println("Initial Vector: " + Arrays.toString(twoValues));

        // Ahora vamos a recorrer esas 10 posiciones y en cada posición vamos a poner un múltiplo de 2
        for (int position = 0; position < twoValues.length; position++) {
            twoValues[position] = position * 2;
        }

        // Ahora el vector tiene los 10 primeros múltiplos de dos
        // Imprimo sus valores después de inicializar
        System.out.println("Values after initialization: " + Arrays.toString(twoValues));

        // ********************************** //
        // Copiar un vector manualmente
        // ********************************** //
        // Defino un vector copia2  para llenarlo de valores, y le digo que su length es la misma que el original
        int[] arrayIntNumbersCopy2 = new int[arrayIntNumbers.length];

        // Imprimo sus valores antes de copiarle los del otro vector, esta todo en 0
        System.out.println("Copy vector before running copy process: " + Arrays.toString(arrayIntNumbersCopy2));

        // Recorremos el vector original para copiarlo a otro nuevo
        for (int position = 0; position < arrayIntNumbers.length; position++) {
            // Agarro el valor que esta en el vector "arrayIntNumbers" en la posición actual y la copio al otro vector
            arrayIntNumbersCopy2[position] = arrayIntNumbers[position];
        }

        // Imprimo sus valores antes de copiarle los del otro vector, esta todo en 0
        System.out.println("Copy vector after running copy process: " + Arrays.toString(arrayIntNumbersCopy2));


        // ********************************** //
        // Algoritmos útiles para trabajar con vectores, estas son operaciones que hacemos habitualmente
        // ********************************** //
        // Definimos un vector con datos para el ejemplo
        int[] data = {1, 2, 3, 4, 5};

        // Recorrer vector de la posicion INICIAL a la FINAL
        for (int posicion = 0; posicion < data.length; posicion++) {
            System.out.println("Start - to - End: " + data[posicion]);
        }

        // Recorrer vector de la posicion FINAL a la INICIAL
        for (int posicion = data.length - 1; posicion >= 0; posicion--) {
            System.out.println("End - to - Start: " + data[posicion]);
        }

        // Búsqueda del numero maximo y mínimo de un vector

        // Inicializamos dos variables con el valor de la posición 0
        int max = data[0];
        int min = data[0];

        // Recorremos en busqueda de los valores
        for (int position = 1; position < data.length; position++) {
            if (data[position] > max) {
                max = data[position];
            }
            if (data[position] < min) {
                min = data[position];
            }
        }
        System.out.println("Max Value: " + max);
        System.out.println("Min Value: " + min);

        // Totalizar un vector y calcular el promedio

        // Inicializo la variable total en 0
        int total = 0;

        // Recorro todo el vector para ir sumando sus posiciones
        for (int position = 0; position < data.length; position++) {
            total += data[position];
        }
        System.out.println("Total sum:" + total);
        System.out.println("Average:" + total / data.length);

        // Buscar cuantas veces aparece un numero
        int[] dataWithDuplicates = {1, 2, 1, 3, 5, 6, 1, 8, 9, 3, 1};

        // Imprimo el nuevo vector
        System.out.println("Array with duplicates values: " + Arrays.toString(dataWithDuplicates));

        // Contador para ver cuantas veces aparece el numero 1
        int counterNumbers1 = 0;
        for (int position = 0; position < dataWithDuplicates.length; position++) {
            // Por cada posición pregunto si es el numero 1
            if (dataWithDuplicates[position] == 1) {
                counterNumbers1++; // Le sumo 1 al contador
            }
        }
        System.out.println("Number 1 is present " + counterNumbers1 + " times");
        
        // ********************************** //
        // Matriz
        // ********************************** //
        
        // Definimos un vector de dos dimensiones
        int[][] matriz;
        
        // Inicializamos el eje x con 4 espacios y el y con 4 espacios
        matriz = new int[4][4];
        
        // Imprimimos la matriz para ver su estado inicial (Tendrá todos ceros)
        System.out.println("Zero Matrix");
        System.out.println(Arrays.toString(matriz[0]));
        System.out.println(Arrays.toString(matriz[1]));
        System.out.println(Arrays.toString(matriz[2]));
        System.out.println(Arrays.toString(matriz[3]));
        
        // Recorremos la matriz para crear la matriz identidad
        // Matriz identidad = Es aquella que tiene 1 en su diagonal y 0 en todos los demas valores
        for (int x = 0; x < matriz.length; x++) {
            int[] fila = matriz[x];
            for (int y = 0; y < fila.length; y++) {
                if (x == y) {
                    matriz[x][y] = 1;
                }
                
            }
        }
        
        // Imprimmos la matriz para esto imprimimos cada fila por separado
        System.out.println("Identity Matrix");
        System.out.println(Arrays.toString(matriz[0]));
        System.out.println(Arrays.toString(matriz[1]));
        System.out.println(Arrays.toString(matriz[2]));
        System.out.println(Arrays.toString(matriz[3]));
    }
}
