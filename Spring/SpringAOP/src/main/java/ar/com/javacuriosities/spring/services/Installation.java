package ar.com.javacuriosities.spring.services;

public class Installation {

    public void start() {
        System.out.println("Starting");
    }

    public void start(String message) {
        System.out.println("Starting " + message);
    }

    public void startWithError() throws Exception {
        System.out.println("Starting with error");
        int result = 1 / 0;
    }

    public boolean startWithReturn() {
        System.out.println("Starting with return");
        return true;
    }
}
