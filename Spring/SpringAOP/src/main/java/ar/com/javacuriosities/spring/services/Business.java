package ar.com.javacuriosities.spring.services;

public class Business {

    public void execute() {
        System.out.println("Executing business logic");
    }

    public void executeWithException() throws Exception {
        System.out.println("Executing business logic with exception");
        int result = 1 / 0;
    }
}
