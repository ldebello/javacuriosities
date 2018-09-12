package ar.com.javacuriosities.spring.services;

import ar.com.javacuriosities.spring.annotations.Loggable;

public class Configuration {

    public void configure() {
        System.out.println("Configuring");
    }

    public void configure(String message) {
        System.out.println("Configuring " + message);
    }

    public void configureWithError() throws Exception {
        System.out.println("Configuring with error");
        int result = 1 / 0;
    }

    public boolean configureWithReturn() {
        System.out.println("Configuring with return");
        return true;
    }

    @Loggable
    public void configureWithAnnotation() {
        System.out.println("Configuring with annotation");
    }
}
