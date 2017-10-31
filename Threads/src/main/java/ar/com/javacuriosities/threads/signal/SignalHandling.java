package ar.com.javacuriosities.threads.signal;


import sun.misc.Signal;
import sun.misc.SignalHandler;

import java.util.concurrent.TimeUnit;

public class SignalHandling {
    public static void main(String[] args) {
        System.out.println("Program Start");
        Kill5Handler kill5Handler = new Kill5Handler();
        Signal.handle(new Signal("TRAP"), kill5Handler);

        try {
            Thread.sleep(TimeUnit.MINUTES.toMillis(1));
        } catch (InterruptedException e) {
            // Log and Handle exception
            e.printStackTrace();
        }

        System.out.println("Program End");
    }

    private static class Kill5Handler implements SignalHandler {
        public Kill5Handler() {
        }

        @Override
        public void handle(Signal signal) {
            System.out.println("Signal Handling");
        }
    }
}