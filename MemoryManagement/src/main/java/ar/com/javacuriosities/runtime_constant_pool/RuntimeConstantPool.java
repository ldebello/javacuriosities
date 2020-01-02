package ar.com.javacuriosities.runtime_constant_pool;

public class RuntimeConstantPool {

    public static void main(String[] args) {
        String s1 = "Hello";
        String s2 = "Hello";
        String s3 = new String("Hello");
        String s4 = new String("Hello");
        String s5 = new String("Hello").intern();
        String s6 = "Hello".intern();

        if (s1 == s2) {
            System.out.println("S1 y S2 estan en el Pool de constantes");
        }

        if (s3 == s4) {
            System.out.println("S3 y S4 NO estan en el Pool de constantes");
        }

        if (s5 == s6) {
            System.out.println("S5 y S6 son iguales");
        }

        if (s1 == s3) {
            System.out.println("S1 esta el pool pero S3 esta en el Heap");
        }

        if (s1 == s5) {
            System.out.println("S1, S2, S5, S6 son iguales");
        }

        if (s2 == s6) {
            System.out.println("S2 y S6 estan en el Pool de constantes");
        }
    }

}
