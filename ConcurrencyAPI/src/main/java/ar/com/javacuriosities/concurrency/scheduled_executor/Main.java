package ar.com.javacuriosities.concurrency.scheduled_executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/*
 * Executors.newScheduledThreadPool() sirve para crear un Pool de 1 solo hilo, el cual 
 * será ejecutado de manera recurrente cada cierto tiempo
 */
public class Main {

    public static void main(String[] args) {
    	try {
	        // Creamos un pool de 1 thread que será ejecutado cada cierto tiempo
	        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
	        // Creamos una tarea que será ejecutada cada 10 segundos
	        final Runnable beeper = new Beeper();
	
	        // Registramos en el scheduler esta tarea para ejecutarla cada 10 segundos sin delay
	        final ScheduledFuture<?> beeperHandler =
	                scheduler.scheduleAtFixedRate(beeper, 0, 10, TimeUnit.SECONDS);
	
	        // Registramos otra tarea para ser ejecutada en 60 segundos
	        scheduler.schedule(new BeeperCancel(beeperHandler), 60, TimeUnit.SECONDS);
        
			scheduler.awaitTermination(90, TimeUnit.SECONDS);
		
			scheduler.shutdown();
		
			while (!scheduler.isTerminated()) {
	        }
		} catch (InterruptedException e) {
			// Log and Handle exception
			e.printStackTrace();
		}
    }
    
    private static final class Beeper implements Runnable {

        public Beeper() {       
        }

        @Override
        public void run() {
            System.out.println("beep");
        }
    }
    
    /*
     * BeeperCancel recibe un ScheduledFuture el cual nos permite
     * interactuar con la tarea que es ejecutada de forma recurrente
     * ya sea de para obtener su valor o cancelarla
     */
    private static final class BeeperCancel implements Runnable {

        private ScheduledFuture<?> beeperHandler;
        
        public BeeperCancel(ScheduledFuture<?> beeperHandler) {   
            this.beeperHandler = beeperHandler;
        }

        @Override
        public void run() {
        	System.out.println("Canceling Beeper");
            beeperHandler.cancel(true);
        }
    }
}