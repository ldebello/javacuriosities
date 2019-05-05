package ar.com.javacuriosities.threads;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * El concepto de Thread Safe indica que un objeto puede
 * ser utilizado por multiples threads sin preocuparnos si esos accesos son concurrentes.
 */
public class Lesson20ThreadSafe {
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) {
        Thread t1 = new Thread(new Formatter("01/01/2019"));
        Thread t2 = new Thread(new Formatter("01/02/2019"));
        Thread t3 = new Thread(new Formatter("01/03/2019"));
        Thread t4 = new Thread(new Formatter("01/04/2019"));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

    private static class Formatter implements Runnable {

        private final String date;

        public Formatter(String date) {
            this.date = date;
        }

        @Override
        public void run() {
            try {
                Date date = FORMATTER.parse(this.date);
                System.out.println(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
