package ar.com.javacuriosities.threads;


public class Step03CommonMethods {

    public static void main(String[] args) {
        // Creamos un thread sobre el cual vamos a trabajar
        Thread worker = new Worker();
        
        /*
         * Por medio del método setName() podemos asignarle el nombre que nosotros
         * queramos sino por defecto su nombre es "Thread-X" donde X es el contador autoincremental.
         * Podemos cambiar su nombre tantas veces como queramos
         */
        worker.setName("Worker 01");
        
        /*
         * La prioridad de los thread esta definida entre un rango de numero desde 1 a 10, estos
         * valores están definidos en constantes en la clase Thread
         * 
         * public final static int MIN_PRIORITY = 1;
         * public final static int NORM_PRIORITY = 5;
         * public final static int MAX_PRIORITY = 10;
         * 
         * La realidad es que el thread pasa estos valores al sistema operativo y hace la conversion
         * a las prioridades que maneja el SO.
         * 
         * Los thread inician con la misma prioridad que tiene su el hilo que los creo a ese momento
         */
        worker.setPriority(Thread.MAX_PRIORITY);
        
        /*
         * Un thread puede ser marcado como thread demonio, por defecto esta propiedad es "false"
         * y se los llama User-Thread o sea hilos de usuarios.
         * 
         * La JVM va a continuar viva hasta que todos los hilos de usuarios hayan terminado, luego
         * se cierra el proceso y los Daemon-Thread son terminados por la JVM 
         */
        worker.setDaemon(false);
        
        /*
         * Dado que los thread pueden ejecutar cualquier código Java también son sensibles a exceptions
         * por eso si un thread termina por una exception que no fue tratada se invoca a un handler
         * por default, este atributo es falso a no ser que asignemos algún objeto para funcionar de handler
         */
        worker.setUncaughtExceptionHandler(null);
        
        
        // Iniciamos el thread
        worker.start();
    }
    
    private static class Worker extends Thread {
    	
		@Override
		public void run() {
			System.out.println("Going from home to work");
			System.out.println("Walking");
			for (int i = 0; i < 100; i++) {
				System.out.print(".");
			}
			System.out.println();
			System.out.println("I have just arrived at work");
		}
    	
    }
}
