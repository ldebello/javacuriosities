package ar.com.javacuriosities.spring.services;

public class Configuration {

    public void configure() {
        System.out.println("Configuring");
    }

    public void configure(String message) {
        System.out.println("Configuring " + message);
    }

    public void configureWithError() {
        System.out.println("Configuring with error");
        int result = 1 / 0;
    }

    public boolean configureWithReturn() {
        System.out.println("Configuring with return");
        return true;
    }
}
