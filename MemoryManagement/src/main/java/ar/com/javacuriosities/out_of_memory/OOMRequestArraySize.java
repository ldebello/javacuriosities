package ar.com.javacuriosities.out_of_memory;

/*
 * Al intentar crear un array que no entra en la memoria Heap recibimos un OOM
 */
public class OOMRequestArraySize {
    public static void main(String[] args) {
        long[] bigArray = new long[Integer.MAX_VALUE];
    }
}
