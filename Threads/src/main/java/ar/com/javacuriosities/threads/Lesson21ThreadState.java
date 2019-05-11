package ar.com.javacuriosities.threads;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Lesson21ThreadState {

    private static final Object mutex = new Object();

    public static void main(String[] args) {
        Thread blockerThread = new Thread(() -> {
            synchronized (mutex) {
                try {
                    TimeUnit.HOURS.sleep(1);
                } catch (InterruptedException e) {
                }
            }
        });

        Thread newThread = new Thread(() -> {
        });

        Thread terminatedThread = new Thread(() -> {
        });

        Thread runnableThread = new Thread(() -> {
            Scanner s = new Scanner(System.in);

            s.nextLine();
        });

        Thread blockedThread = new Thread(() -> {
            synchronized (mutex) {

            }
        });

        Thread waitingThread = new Thread(() -> {
            synchronized (Thread.currentThread()) {
                try {
                    Thread.currentThread().wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread timeWaitingThread = new Thread(() -> {
            synchronized (Thread.currentThread()) {
                try {
                    Thread.currentThread().wait(TimeUnit.HOURS.toMillis(1));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        blockerThread.start();
        terminatedThread.start();
        runnableThread.start();
        blockedThread.start();
        waitingThread.start();
        timeWaitingThread.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }

        System.out.println("Thread State: " + newThread.getState());
        System.out.println("Thread State: " + runnableThread.getState());
        System.out.println("Thread State: " + terminatedThread.getState());
        System.out.println("Thread State: " + blockedThread.getState());
        System.out.println("Thread State: " + waitingThread.getState());
        System.out.println("Thread State: " + timeWaitingThread.getState());
    }
}
