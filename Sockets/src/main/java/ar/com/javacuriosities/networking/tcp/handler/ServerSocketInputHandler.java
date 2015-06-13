package ar.com.javacuriosities.networking.tcp.handler;

public class ServerSocketInputHandler {

    public String processInput(String currentInput) {
        String output = "Welcome - Waiting commands";
        if (currentInput != null) {
            if ("Greetings".equalsIgnoreCase(currentInput)) {
                output = "Welcome";
            } else if ("Write".equalsIgnoreCase(currentInput)) {
                output = "Writing data";
            } else if ("Read".equalsIgnoreCase(currentInput)) {
                output = "Reading data";
            } else if ("Exit".equalsIgnoreCase(currentInput)) {
                output = "Bye";
            } else {
                output = "Invalid Command";
            }
        }
        return output;
    }
}