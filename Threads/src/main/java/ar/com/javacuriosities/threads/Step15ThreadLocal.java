package ar.com.javacuriosities.threads;


public class Step15ThreadLocal {

    public static void main(String[] args) {
        try {
            // Creamos un recurso compartido
            SharedResource sharedResource = new SharedResource();

            // Creamos 3 thread que comparten el mismo recurso
            Thread thread1 = new Thread(new Task(20, sharedResource));
            Thread thread2 = new Thread(new Task(30, sharedResource));
            Thread thread3 = new Thread(new Task(50, sharedResource));

            thread1.setName("Thread-01 (20 Tasks)");
            thread2.setName("Thread-02 (30 Tasks)");
            thread3.setName("Thread-03 (50 Tasks)");
            
            // Iniciamos cada hilo
            thread1.start();
            thread2.start();
            thread3.start();
            
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
        	// Log and Handle exception
        	e.printStackTrace();
        }
    }
    
    /*
     * Esta clase simula un recurso compartido el cual será usado por varios threads,
     * el mismo posee dos atributos el cual uno se encuentra usando ThreadLocal lo cual
     * implica que cada Thread tendrá su propio valor
     */
    private static final class SharedResource {
    	
        private int counter = 0;
        private ThreadLocal<Integer> localCounter = new ThreadLocal<Integer>();

        public SharedResource() {
        }

        public int getCounter() {
            return counter;
        }

        /*
         * Este método devuelve el valor del contador asociado al hilo actual
         */
        public Integer getLocalCounter() {
            if (localCounter.get() == null) {
                localCounter.set(Integer.valueOf(0));
            }
            return localCounter.get();
        }
        
        public synchronized void incrementCounter() {
        	counter++;
        }
        
        public synchronized void incrementLocalCounter() {
            localCounter.set(getLocalCounter() + 1);
        }    
    }
    
    /*
     * Esta tarea utiliza un recurso compartido sobre el cual incrementa el valor de las propiedades
     * del recurso
     */
    private static final class Task implements Runnable {

        private int loopCounter;
        private SharedResource sharedResource;

        public Task(int loopCounter, SharedResource sharedResource) {
            this.loopCounter = loopCounter;
            this.sharedResource = sharedResource;
        }

        @Override
        public void run() {
            for (int i = 1; i <= loopCounter; i++) {
            	sharedResource.incrementCounter();
            	sharedResource.incrementLocalCounter();
            }

            String threadName = Thread.currentThread().getName();
            
            /*
             * Aquí imprimimos el valor de la variable que es compartida por todos los threads
             */
            System.out.println("Thread:" + threadName + " Counter value: " + sharedResource.getCounter());
            
            /*
             * Aquí imprimimos el valor del contador local porque este esta definido como ThreadLocal
             * o sea cada thread que lo usa tiene su propio valor por eso es que el máximo valor de cada contador 
             * local es el máximo numero de iteraciones
             */
            System.out.println("Thread:" + threadName + " Local counter value: " + sharedResource.getLocalCounter());
        }
    }
}