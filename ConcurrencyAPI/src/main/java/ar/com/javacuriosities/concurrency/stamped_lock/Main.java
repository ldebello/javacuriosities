package ar.com.javacuriosities.concurrency.stamped_lock;

import java.security.SecureRandom;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.StampedLock;

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
        private final StampedLock lock = new StampedLock();

        private int x;
        private int y;

        public int readPosition(final int[] coordinates) {
            int tries = 1;
            long stamp = lock.tryOptimisticRead();

            coordinates[0] = x;
            coordinates[1] = y;

            if (!lock.validate(stamp)) {
                ++tries;

                stamp = lock.readLock();
                try {
                    coordinates[0] = x;
                    coordinates[1] = y;
                } finally {
                    lock.unlockRead(stamp);
                }
            }

            System.out.println(Thread.currentThread().getName() + " Reading after " + tries + " tries - x: " + coordinates[0] + " y: " + coordinates[1]);

            return tries;
        }

        public int move(final int x, final int y) {
            final long stamp = lock.writeLock();
            try {
                this.x = x;
                this.y = y;
                System.out.println(Thread.currentThread().getName() + " Writing x: " + x + " y: " + y);
            } finally {
                lock.unlockWrite(stamp);
            }

            return 1;
        }
    }
}
