package ar.com.javacuriosities.threads;


public class Lesson04JoinMethod {

    public static void main(String[] args) {
        // Creamos 3 threads
        Thread worker01 = new Worker("Cosme Fulanito");
        Thread worker02 = new Worker("Pablo Marmol");
        Thread worker03 = new Worker("Don Quijote");

        try {
            /*
             * Iniciamos el primer Thread y luego ejecutamos el método join()
             * por lo cual el Thread actual se frenara hasta que el thread sobre
             * el cual se hizo join() termine
             */
            worker01.start();
            worker01.join();

            /*
             * Aquí iniciamos los dos threads restantes
             */
            worker02.start();
            worker03.start();

            /*
             * Ahora el hilo actual, o sea el que inicio el Main se queda
             * esperando por el worker02 y worker03, el join() detiene el thread
             * actual hasta que el cual se hizo join() termine y si el thread
             * ese ya termino continua ejecutando el thread actual
             */
            worker02.join();
            worker03.join();
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        }
    }

    private static final class Worker extends Thread {

        public Worker(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println(getName() + " is going from home to work");
            System.out.println("Walking");
            for (int i = 0; i < 100; i++) {
                System.out.print(".");
            }
            System.out.println();
            System.out.println(getName() + " has just arrived at work");
        }
    }
}