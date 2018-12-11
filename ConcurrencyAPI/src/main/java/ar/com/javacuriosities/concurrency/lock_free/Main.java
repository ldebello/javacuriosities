package ar.com.javacuriosities.concurrency.lock_free;

import java.security.SecureRandom;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

public class Main {

    private static final int NUMBER_OF_THREADS = 5;

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    public static void main(String[] args) {
        Spaceship spaceship = new Spaceship();

        // Creamos un Pool de 5 Threads
        ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

        for (int i = 1; i <= 10; i++) {
            executor.execute(new Writer(spaceship));
        }
        for (int i = 1; i <= 10; i++) {
            executor.execute(new Reader(spaceship));
        }

        // No aceptamos nuevas tareas
        executor.shutdown();

        while (!executor.isTerminated()) {
        }
    }

    /*
     * Creamos un Thread que van a ejecutar operaciones de escritura Un solo
     * escritor puede estar al mismo tiempo.
     */
    private static final class Writer implements Runnable {

        private Spaceship spaceship;

        public Writer(Spaceship spaceship) {
            this.spaceship = spaceship;
        }

        @Override
        public void run() {
            int x = SECURE_RANDOM.nextInt(1000);
            int y = SECURE_RANDOM.nextInt(1000);

            spaceship.move(x, y);
        }
    }

    /*
     * Esta clase representa los lectores, estos puede haber ejecutar lecturas
     * de forma concurrente pero no pueden leer si un escritor
     */
    private static final class Reader implements Runnable {

        private Spaceship spaceship;

        public Reader(Spaceship spaceship) {
            this.spaceship = spaceship;
        }

        @Override
        public void run() {
            int[] coordinates = new int[2];
            spaceship.readPosition(coordinates);
        }
    }

    private static class Spaceship {
        private final AtomicReference<Position> position = new AtomicReference<>(new Position(0, 0));

        public int readPosition(final int[] coordinates) {
            final Position currentPosition = position.get();
            coordinates[0] = currentPosition.getX();
            coordinates[1] = currentPosition.getY();

            System.out.println(Thread.currentThread().getName() + " Reading  x: " + coordinates[0] + " y: " + coordinates[1]);

            return 1;
        }

        public int move(final int x, final int y) {
            int tries = 0;
            Position currentPosition;

            do {
                ++tries;
                currentPosition = position.get();
            }
            while (!position.compareAndSet(currentPosition, currentPosition.move(x, y)));
            System.out.println(Thread.currentThread().getName() + " Writing x: " + x + " y: " + y);
            return tries;
        }
    }

    private static class Position {
        private final int x;
        private final int y;

        Position(final int x, final int y) {
            this.x = x;
            this.y = y;
        }

        int getX() {
            return x;
        }

        int getY() {
            return y;
        }

        Position move(final int x, final int y) {
            return new Position(x, y);
        }
    }
}
