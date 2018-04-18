package ar.com.javacuriosities.basics;

public class Lesson03ConcatenationOperator {

    public static void main(String[] args) {
        System.out.println("Lesson 03 - Concatenation Operator");

        /*
         * Concatenar es unir dos pedazos de texto para formar uno solo
         * Ejemplo
         * String text1 = "Hola ";
         * String text2 = "-";
         * String text3 = "Mundo";
         * 
         * En Java se usa el operador + para sumar siempre y cuando los operandos sean
         * numero si alguno de los operandos es texto, y su funcionamiento pasa a ser de concatenación
         * 
         * String result = text1 + text2;
         * La variable result tendría el texto "Hola -"
         * 
         * result = text1 + text2 + text3;
         * La variable result tendría el texto "Hola -Mundo"
         */

        // Aca definimos 3 variables de tipo String que cada una tiene una frase, palabra o símbolo o cualquier
        // combinación de caracteres valida
        String text1 = "Hola ";
        String text2 = "-";
        String text3 = "Mundo";
        
        // Aca definimos una variable llamada result la cual la inicializamos como la concatenación de los 3 textos definidos arriba
        String result = text1 + text2 + text3;
        
        // Imprimimos el valor de la variable result
        System.out.println(result);
        
        // Aca directamente imprimo la concatenación de text1 y text3
        System.out.println(text1 + text3);
        
        /*
         * El result de usar el operador + esta condicionado por el
         * tipo de dato de los operandos
         * 
         * tipo numérico + tipo numérico = calcula la suma y devuelve el valor
         * tipo char + tipo numérico = convierte el numero en texto y hace la concatenación
         * tipo String + tipo numérico = convierte el numero en texto y hace la concatenación
         * 
         * tipo char + tipo char = Realiza la concatenación
         * tipo String + tipo String = Realiza la concatenación
         * tipo char + tipo String = Realiza la concatenación
         * tipo String + tipo char = Realiza la concatenación
         */
    }
}
