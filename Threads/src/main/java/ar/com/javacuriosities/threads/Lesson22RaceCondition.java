package ar.com.javacuriosities.threads;

public class Lesson22RaceCondition {

    private static int counter = 0;

    public static void main(String[] args) {
        Counter counter1 = new Counter();
        Counter counter2 = new Counter();

        Thread t1 = new Thread(counter1);
        Thread t2 = new Thread(counter2);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Total: " + counter);
    }

    private static class Counter implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                counter++;
            }
        }
    }
}
