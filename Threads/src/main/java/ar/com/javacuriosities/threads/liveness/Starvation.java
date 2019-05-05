package ar.com.javacuriosities.threads.liveness;

import java.util.concurrent.TimeUnit;

/*
 * Cuando hablamos de Starvation esto esta ligado a la prioridad de cada thread, esto ocurre cuando un thread con menor prioridad que otro no puede avanzar
 * porque esta constantemente esperando para obtener a lock, aunque eso no sucede porque hay threads con prioridades mayores los cuales obtienen el lock
 */
public class Starvation {

    public static void main(String[] args) {
        BankAccount account1 = new BankAccount(1, 500d);
        BankAccount account2 = new BankAccount(2, 500d);

        int availableProcessors = Runtime.getRuntime().availableProcessors();

        // Creamos muchos monitores para incrementar las posibilidades de Starvation
        for (int i = 0; i < availableProcessors * 2; i++) {
            Thread monitor = new Thread(new BalanceMonitor(account1), "BalanceMonitor-" + i);
            monitor.setPriority(Thread.MAX_PRIORITY);
            monitor.start();
        }

        Thread transfer1 = new Thread(new TransferTask(account1, account2, 250d), "TransferTask-1");
        Thread transfer2 = new Thread(new TransferTask(account1, account2, 250d), "TransferTask-2");

        transfer1.setPriority(Thread.MIN_PRIORITY);
        transfer2.setPriority(Thread.MIN_PRIORITY);

        // Luego iniciamos las transferencias
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            // Log and Handle exception
            e.printStackTrace();
        }

        transfer1.start();
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
            long start = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + " Started execution");
            from.transfer(to, amount);
            System.out.println(Thread.currentThread().getName() + " Completed execution " + (System.currentTimeMillis() - start) + " ms");
        }
    }

    private static final class BalanceMonitor implements Runnable {
        private BankAccount account;

        public BalanceMonitor(BankAccount account) {
            this.account = account;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " Started execution");
            while (true) {
                if (account.getBalance() <= 0) {
                    // Aquí enviamos un email, sms o alguna forma de notificación
                    break;
                }
            }
            System.out.println(Thread.currentThread().getName() + " Account has gone too low, email sent, exiting");
        }
    }


    private static final class BankAccount {
        private int id;
        private double balance;

        public BankAccount(int id, double balance) {
            this.id = id;
            this.balance = balance;
        }

        public synchronized double getBalance() {
            // Simulamos algo de IO como por ejemplo acceso a una DB
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                // Log and Handle exception
                e.printStackTrace();
            }
            return balance;
        }

        public synchronized void withdraw(double amount) {
            balance -= amount;
        }

        public synchronized void deposit(double amount) {
            balance += amount;
        }

        public synchronized void transfer(BankAccount to, double amount) {
            withdraw(amount);
            to.deposit(amount);
        }

        @Override
        public String toString() {
            return "BankAccount [id=" + id + ", balance=" + balance + "]";
        }
    }
}