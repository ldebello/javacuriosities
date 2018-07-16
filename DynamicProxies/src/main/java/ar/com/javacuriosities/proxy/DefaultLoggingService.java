package ar.com.javacuriosities.proxy;

public class DefaultLoggingService implements LoggingService {
    @Override
    public void log(String message) {
        System.out.println("Message: " + message);
    }
}
