package ar.com.javacuriosities.ws.exceptions;

public class AddNumbersException extends Exception {
    String detail;

    public AddNumbersException (String message, String detail) {
        super (message);
        this.detail = detail;
    }

    public String getDetail () {
        return detail;
    }
}