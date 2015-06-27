package ar.com.javacuriosities.threads;

import java.util.Random;

public class Step16ThreadHandler {

    public static void main(String[] args) {
        // Creamos 3 threads los cuales pueden arrojar exception 
		Thread thread1 = new Thread(new ErrorGenerator());
		Thread thread2 = new Thread(new ErrorGenerator());
		Thread thread3 = new Thread(new ErrorGenerator());

        // Creamos un manejador de exceptions
        HandlerException handler = new HandlerException();
        
        // Asignamos a cada hilo el manejador
		thread1.setUncaughtExceptionHandler(handler);
		thread2.setUncaughtExceptionHandler(handler);
		thread3.setUncaughtExceptionHandler(handler);
        
        // Iniciamos cada hilo
        thread1.start();
        thread2.start();
        thread3.start();
    }
    
    /*
     * Usamos un Runnable que de forma aleatoria arroja error
     */
    private static final class ErrorGenerator implements Runnable {

        public ErrorGenerator() {
        }

        @Override
        public void run() {
            Random number = new Random();
            int result = 100 / number.nextInt(3);
            System.out.println("Result: " + result);
        }
    }
    
    private static final class HandlerException implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.printf("Thread with error: %s \n", t.getName());
        }
    }
}