package ar.com.javacuriosities.concurrency.count_down_latch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * CountDownLatch: Nos permitirá establecer el número de eventos que deseamos que ocurran,  antes de continuar con la ejecución. 
 * Para indicar que debe esperar invocaremos al método await() y para ir disminuyendo  el número de eventos usaremos el método 
 * countDown().
 */
public class Main {

    public static void main(String[] args) {
        // Creamos un contador con tres eventos
        CountDownLatch countDownLatch = new CountDownLatch(3);
        
        // Creamos el thread y le pasamos su contado
        new Task(countDownLatch).start();
        try {
            System.out.println("Waiting for processes...");
            
            // Esperamos que el contador llegue a cero
            countDownLatch.await();
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Finish");
    }
    
    /*
     * Task simula varios procesos
     */
    private static final class Task extends Thread {

        private CountDownLatch countDownLatch;

        public Task(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                // Realizamos 3 procesos
                System.out.println("Executing process 1...");
                TimeUnit.SECONDS.sleep(2);
                // Cada llamada al count down resta uno a la cuenta final
                countDownLatch.countDown();
                
                System.out.println("Executing process 2...");
                TimeUnit.SECONDS.sleep(2);
                countDownLatch.countDown();
                
                System.out.println("Executing process 3...");
                TimeUnit.SECONDS.sleep(2);
                countDownLatch.countDown();
            } catch (InterruptedException e) {
            	// Log and Handle exception
				e.printStackTrace();
            }
        }
    }
}