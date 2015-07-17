package ar.com.javacuriosities.concurrency.fork_join;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/*
 * El framework Fork/Join (Incluido en Java 1.7) es una implementación de ExecutorService con mejoras para el multiproceso. 
 * Está diseñado para dividir una tarea grande en varias pequeñas. El objetivo es aprovechar mejor 
 * toda la capacidad de procesamiento y mejorar el rendimiento de las aplicaciones. 
 * Al igual que con ExecutorService, tendremos un pool de Threads con una cola de tareas asociada, 
 * la diferencia estará en la forma de procesar estas tareas. 
 * El framework fork/join implementa el algoritmo work-stealing, que dice que los threads de trabajo que 
 * quedan sin cosas que hacer pueden robar tareas de otros threads que aún están ocupados. 
 * Dicho de otra manera, dice que un thread que está a la espera la finalización de otros threads, 
 * busca tareas que no han sido ejecutados y los ejecuta.
 * 
 * Veamos las clases principales del framework
 * 
 * La clase java.util.concurrent.ForkJoinPool implementa la interfaz ExecutorSevice y también implementa el algoritmo work-stealing.
 * 
 * La clase java.util.concurrent.ForkJoinTask implementa la interfaz Future, la cual 
 * es una clase abstracta para las tareas que se ejecutan en el ForkJoinPool, provee el método fork() 
 * para organizar la ejecución asíncrona y el método join() para continuar hasta que el resultado de la tarea se ha calculado.
 * 
 * La clase java.util.concurrent.RecursiveAction, esta tarea implementa su lógica en el método compute(), 
 * este método suele tener la siguiente lógica
 *
 * if (el problema es lo suficientemente pequeño) {
 * 	resolvemos el problema
 * } else {
 *  fork: dividimos el problema en partes pequeñas
 *  join: esperamos por cada parte a ser resuelta
 *  compose: juntamos los distintos resultados
 * }
 * 
 */
public class Main {

	public static void main(String[] args) {
		// Creamos un arreglo con numeros random
		int[] data = new int[1000];
		
		Random random = new SecureRandom();
		
		for (int i = 0; i < data.length; i++) {
			data[i] = random.nextInt(50000);
		}

		ForkJoinPool pool = new ForkJoinPool();
		MaximumFinder finder = new MaximumFinder(data);
		System.out.println("The maximum value is: " + pool.invoke(finder));
	}

	private static final class MaximumFinder extends RecursiveTask<Integer> {

		private static final long serialVersionUID = 1L;

		private static final int SEQUENTIAL_THRESHOLD = 5;

		private final int end;
		private final int start;
		private final int[] data;

		public MaximumFinder(int[] data) {
			this(data, 0, data.length);
		}
		
		public MaximumFinder(int[] data, int start, int end) {
			this.end = end;
			this.data = data;
			this.start = start;
		}

		@Override
		protected Integer compute() {
			int length = end - start;
			if (length < SEQUENTIAL_THRESHOLD) {
				return computeDirectly();
			}
			
			int split = length / 2;
			MaximumFinder left = new MaximumFinder(data, start, start + split);
			MaximumFinder right = new MaximumFinder(data, start + split, end);
			
			left.fork();
			return Math.max(right.compute(), left.join());
		}

		private Integer computeDirectly() {
			System.out.println(Thread.currentThread() + " computing: " + start + " to " + end);
			int max = Integer.MIN_VALUE;
			for (int i = start; i < end; i++) {
				if (data[i] > max) {
					max = data[i];
				}
			}
			return max;
		}
	}
}