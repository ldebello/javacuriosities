package ar.com.javacuriosities.concurrency.callable_future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
 * En esta caso vamos a revisar dos interfaces incluidas en Java 1.5 Future & Callable.
 * 
 * Callable: Es la representación de una tarea al igual que una Runnable con la diferencia, que Callable puede
 * devolver el resultado de la tarea o arrojar excepciones.
 * 
 * Future: Representa el estado de una operación asincronica, dado que esta ligado a una tarea la cual no conocemos su estado para
 * obtener el resultado final podes usar el método get() que es bloqueante y esperar hasta la respuesta o el mismo método pero parametrizado para 
 * definir una cantidad de tiempo.
 * 
 * Para poder ejecutar un Callable necesitamos hacer uso del Executor framework (También incluido Java 1.5 el cual ofrece varias funcionales y entre ellas Thread pools).
 */
public class Main {
	public static void main(String[] args) {
		// Creamos un Pool de un solo thread
		ExecutorService executor = Executors.newSingleThreadExecutor();
		
		// Aquí enviamos nuestro Callable a ser enviado
		Future<Integer> future = executor.submit(new CustomCallableTask());
		
		try {
			// El método get() es bloqueante por lo cual esperamos hasta terminar la ejecución
			Integer result = future.get();
			System.out.println("Result: " + result);
		} catch (InterruptedException | ExecutionException e) {
			// Log and Handle exception
        	e.printStackTrace();
		}
		
		// Iniciamos la detención del pool de forma ordenada
		executor.shutdown();
		
		// Esperamos que terminen todos los Threads
        while (!executor.isTerminated()) {
        }
	}

	private static final class CustomCallableTask implements Callable<Integer> {

		@Override
		public Integer call() throws Exception {
			int total = 0;
			for (int i = 0; i <= 100; i++) {
				total += i;
			}
			return total;
		}
	}
}
