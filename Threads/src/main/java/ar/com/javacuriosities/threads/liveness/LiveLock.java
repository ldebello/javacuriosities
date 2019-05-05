package ar.com.javacuriosities.threads.liveness;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Un LiveLock es similar a un Deadlock en sentido de que dos o mas threads se están bloqueando entre si, pero con un Livelock cada thread esta esperando "activamente",
 * intentando resolver su problema, como por ejemplo librando sus locks y reintentando su tarea
 */
public class LiveLock {

    public static void main(String[] args) {
        BankAccount account1 = new BankAccount(1, 500d);
        BankAccount account2 = new BankAccount(2, 500d);

        Thread transfer1 = new Thread(new TransferTask(account1, account2, 10d), "TransferTask-1");
        transfer1.start();

        Thread transfer2 = new Thread(new TransferTask(account2, account1, 10d), "TransferTask-2");
        transfer2.start();
    }

    private static final class TransferTask implements Runnable {
        private double amount;
        private BankAccount from;
        private BankAccount to;

        public TransferTask(BankAccount from, BankAccount to, double amount) {
            this.from = from;
            this.to = to;
            this.amount = amount;
        }

        @Override
        public void run() {
            while (!from.tryTransfer(to, amount)) {
                System.out.println(from + " is waiting for: " + to);
                continue;
            }
            System.out.printf(Thread.currentThread().getName() + " Completed");
        }

    }

    private static final class BankAccount {
        private int id;
        private double balance;
        private Lock lock = new ReentrantLock();

        public BankAccount(int id, double balance) {
            this.id = id;
            this.balance = balance;
        }

        public boolean withdraw(double amount) {
            if (this.lock.tryLock()) {
                // Simulamos algo de IO como por ejemplo acceso a una DB
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    // Log and Handle exception
                    e.printStackTrace();
                }
                balance -= amount;
                return true;
            }
            return false;
        }

        public boolean deposit(double amount) {
            if (this.lock.tryLock()) {
                // Simulamos algo de IO como por ejemplo acceso a una DB
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    // Log and Handle exception
                    e.printStackTrace();
                }
                balance += amount;
                return true;
            }
            return false;
        }

        public boolean tryTransfer(BankAccount destinationAccount, double amount) {
            if (this.withdraw(amount)) {
                if (destinationAccount.deposit(amount)) {
                    return true;
                } else {
                    // La cuenta de destino esta ocupada así que hacemos un refund en la cuenta origen
                    this.deposit(amount);
                }
            }

            return false;
        }

        @Override
        public String toString() {
            return "BankAccount [id= " + id + ", balance= " + balance + "]";
        }
    }
}