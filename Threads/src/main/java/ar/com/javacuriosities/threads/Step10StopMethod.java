package ar.com.javacuriosities.threads;


public class Step10StopMethod {

    @SuppressWarnings("deprecation")
	public static void main(String[] args) {
        try {
            // Creamos a thread para imprimir numeros
            Thread counter = new Counter();

            counter.start();

            // Dormimos el thread actual por 20 milisegundos, para darle tiempo al otro thread
            Thread.sleep(20);
            
            /*
             * Destruimos el thread counter
             * Es importante notar que el método stop() esta deprecado dado  que puede
             * dejar recursos en estados inconsistente cuando ejecutamos el stop() se liberan
             * todos los monitores usados por ese thread
             * Como se puede ver el ciclo no siempre llega a ejecutarse completamente
             */
            counter.stop();
        } catch (InterruptedException e) {
        	// Log and Handle exception
        	e.printStackTrace();
        }
    }
    
    private static final class Counter extends Thread {
    	@Override
    	public void run() {
    		for (int i = 0; i < 9999; i++) {
				System.out.println("Current number: " + i);
			}
    	}
    }
}