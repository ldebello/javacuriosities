package ar.com.javacuriosities.concurrency.atomic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * El paquete java.util.concurrent.atomic.*; contiene muchas clases útiles para realizar operaciones atómicas, una operación atómica una
 * operación que se ejecuta de forma total nunca parcial.
 * 
 * La operación "c++" incrementa la variable "c" en uno pero esto consta de tres operaciones
 * 1- Lectura del valor actual de "c"
 * 2- Incremento del valor
 * 3- Asignar el resultado a la variable "c"
 * 
 * Por ende para lograr que esto se maneje de forma atómica debemos usar sincronización o alguna otra técnica, las clases del paquete atomic
 * hacen uso de técnicas como CAS (Compare-And-Swap) lo cual evita la sincronización y asegura la atomicidad
 *  
 * CAS Loop:
 * for (;;) {
 * 	int current = get();
 * 	int next = current + 1;
 * 	if (compareAndSet(current, next)) {
 * 		return next;
 * 	}
 * }
 * 
 * Nota: Es importante notar que a partir de Java 1.8 se modificaron algunas de estas clases para no usar CAS y utilizar XADD (Fetch and Add)
 * 
 * Update Java 1.8:
 * En Java 1.8 se agregaron clases como 
 * 	- LongAdder: Esta clase es la alternativa a AtomicLong cuando varios thread actualizan un mismo valor, dado que el algoritmo que utiliza es mas eficiente, continua usando el algoritmo
 * 	CAS pero cuando falla almacena los valores pendientes en un objeto Cell que luego será utilizado
 * 
 * 	- LongAccumulator: Esta clase es una version mas especializada de LongAdder la cual nos permite trabajar con lambdas del tipo LongBinaryOperator
 */
public class Main {

    private static final int MAX_THREADS = 10;

    public static void main(String[] args) {
        // Definimos un solo contador que será compartido por todos los threads
        final AtomicCounter counter = new AtomicCounter();

        // Creamos la lista de Futures que tendrá el resultado de cada Thread
        List<Future<Integer>> results = new ArrayList<Future<Integer>>();

        // Creamos un pool de 10 hilos
        ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);

        // Creamos 50 tareas y cada tarea usa el contador compartido por todos
        for (int i = 0; i < 50; i++) {
            Callable<Integer> worker = new Task(counter);
            Future<Integer> submit = executor.submit(worker);
            results.add(submit);
        }

        // Iniciamos la finalización de forma ordenada y no aceptarán nuevas tareas 
        executor.shutdown();

        // Esperamos que terminen todos los Threads
        while (!executor.isTerminated()) {
        }

        // Obtenemos los valores en un Set que no acepta repetidos
        Set<Integer> set = new HashSet<Integer>();

        // Recorremos los resultados
        for (Future<Integer> future : results) {    
			try {
				set.add(future.get());
			} catch (InterruptedException | ExecutionException e) {
				// Log and Handle exception
				e.printStackTrace();
			}
        }

        // Chequeo para ver que todas las operaciones hayan sido realizadas de forma atómica
        if (results.size() != set.size()) {
            throw new RuntimeException("There are some entries with the same value!!!");
        }
    }
    
    /*
     * Usamos un wrapper el cual contiene una instancia de AtomicInteger
     * Si nos usáramos AtomicInteger podría suceder que las operaciones no se ejecuten de forma atómica por ende podríamos perder numeros.
     */
    private static final class AtomicCounter {

        private AtomicInteger value = new AtomicInteger();

        public int increment() {
            // incrementAndGet: incrementa en 1 el valor actual y lo devuelve. 
            return value.incrementAndGet();
        }
    }
    
    private static final class Task implements Callable<Integer> {
    	private AtomicCounter counter;

		public Task(AtomicCounter counter) {
			this.counter = counter;
		}

		@Override
        public Integer call() throws Exception {
            int number = counter.increment();
            System.out.println("Task number:" + number);
            return number;
        }
    }
}
