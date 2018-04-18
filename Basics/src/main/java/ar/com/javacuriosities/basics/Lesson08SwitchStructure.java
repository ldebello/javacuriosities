package ar.com.javacuriosities.basics;

public class Lesson08SwitchStructure {

    public static void main(String[] args) {
        System.out.println("Lesson 08 - Switch Structure");

        /*
         * La estructura Switch se puede usar con números (byte, short, int) o chars, a partir de Java 7
         * se pueden usar Strings, la idea de esta estructura es ofrecer una lista de opciones
         * y en base a una variable decidir porque camino ir
         * También podemos usar esta estructura con objetos Enum (Desde Java 1.5)
         */

        // ********************************** //
        // Switch números
        // ********************************** //

        // Defino una variable que se llama gateNumber y vale 1
        int gateNumber = 2;

        // Usamos un switch que revisa cual es el valor de la variable y va al case correspondiente
        switch (gateNumber) {
            case 1:
                System.out.println("Gate number 1");
                break; // Es importante poner el break por cada case, si no lo ponemos sigue ejecutando el código de abajo hasta el break que encuentre
            case 2:
                System.out.println("Gate number 2");
                break; // Es importante poner el break por cada case, si no lo ponemos sigue ejecutando el código de abajo hasta el break que encuentre
            case 3:
                System.out.println("Gate number 3");
                break; // Es importante poner el break por cada case, si no lo ponemos sigue ejecutando el código de abajo hasta el break que encuentre
            default: // El default es opcional, si ningún case corresponde a la variable viene por el default
                System.out.println("Default Gate");
                break;
        }

        // Ejemplo en el cual nos olvidamos el break
        switch (gateNumber) {
            case 1:
                System.out.println("Case without break - Gate number 1");
                break;
            case 2:
                System.out.println("Case without break - Gate number 2");
            case 3:
                System.out.println("Case without break - Gate number 3");
                //break; // Si este break no estuviera comentado, frenaría aca y no ejecutaría el default
            default:
                System.out.println("Case without break - Default Gate");
                break;
        }
    }
}
