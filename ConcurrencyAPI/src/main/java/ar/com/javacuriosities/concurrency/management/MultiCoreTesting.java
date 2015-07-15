package ar.com.javacuriosities.concurrency.management;

import java.lang.management.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

public class MultiCoreTesting {

	private static final int NUMBER_OF_THREADS = 16;
    
    // Creamos un CountDown de un solo thread
    private static CountDownLatch countDownLatch = new CountDownLatch(NUMBER_OF_THREADS);
    
    // Definimos una operación atómica para evitar perder valores por distintos threads
    private static AtomicLong total = new AtomicLong();

    public static void main(String[] args) throws InterruptedException {
        // Preguntamos la cantidad de procesadores disponibles
        System.out.println("Available Processors: " + Runtime.getRuntime().availableProcessors());
        
        // Calculamos el "wall clock time" en nano segundos
        long elapsedTime = System.nanoTime();
        
        // Creamos y empezamos a ejecutar tantos hilos como diga nuestra constante
        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            Thread thread = new Thread(new Task());
            thread.start();
        }
        
        // Esperamos todos los hilos
        countDownLatch.await();
        
        // Calculamos el tiempo, desde el punto de vista del usuario
        elapsedTime = System.nanoTime() - elapsedTime;
        
        /*
         * Informamos los dos tiempos
         * Elapsed time: Tiempo de vista por el usuario
         * CPU Time: Tiempo consumido por el procesador
         */
        System.out.println("Total elapsed time " + elapsedTime);
        System.out.println("Total thread CPU time " + total.get());
        
        // Calculamos el factor (Total CPU Time / Elapsed Time)
        double factor = total.get();
        factor /= elapsedTime;
        
        // Imprimimos el factor
        System.out.printf("Factor: %.2f%n", factor);
        
        /*
         * El factor debe ser un numero igual o cercano al numero de procesadores disponibles
         * 
         * Runtime.getRuntime().availableProcessors() para indicarnos que varios 
         * Threads están corriendo de forma concurrente.
         * 
         * Ejemplo:
         * Si Tenemos 4 procesadores y corremos 4 hilos el factor deberla ser cercano a 4
         * Si Tenemos 4 procesadores y corremos 8 hilos el factor va a ser un numero cercano a 4 
         * porque solo podemos ejecutar 4 hilos de forma concurrente
         * Si Tenemos 4 procesadores y corremos 2 hilos el factor va a ser un numero cercano a 2
         */
    }

    private static long measureThreadCpuTime() {
        // Pedimos el ThreadMXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        
        // Pedimos el cpuTime inicial para el hilo actual
        long cpuTime = threadMXBean.getCurrentThreadCpuTime();
        
        /* Creamos una variable la cual vamos a ir modificando en el ciclo
         * este código esta puesto porque si ponemos un ciclo vació el compilador
         * se da cuenta de esto y lo remueve para incrementar la performance
         */
        long total = 0;
        for (int i = 0; i < 1000 * 1000 * 1000; i++) {            
            total += i;
            total *= 10;
        }
        
        // Calculamos la diferencia entre el tiempo final y el inicial
        cpuTime = threadMXBean.getCurrentThreadCpuTime() - cpuTime;
        
        /*
         * Imprimimos el valor del total para mostrar que siempre es igual, además
         * mostramos el CPU Time y devolvemos el cpu time consumido para después hacer
         * la cuenta del factor
         */
        System.out.println("Total: " + total + " - " + Thread.currentThread() + ": cpuTime = " + cpuTime);
        return cpuTime;
    }
    
    private static final class Task implements Runnable {
    	@Override
        public void run() {
            total.addAndGet(measureThreadCpuTime());
            countDownLatch.countDown();
        }
    }
}