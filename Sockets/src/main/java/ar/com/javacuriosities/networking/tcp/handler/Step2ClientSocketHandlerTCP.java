package ar.com.javacuriosities.networking.tcp.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Step2ClientSocketHandlerTCP {

    public static void main(String[] args) throws IOException {
        // Creamos un Socket que va a enviar datos al puerto 4444
        try (Socket socket = new Socket(InetAddress.getLocalHost(), 4444)) {
            // Usamos un PrintWriter para enviar datos, o sea usaremos un stream de caracteres
            PrintWriter socketOutput = new PrintWriter(socket.getOutputStream(), true);

            // Buffer de lectura del server
            BufferedReader socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Buffer de lectura para la entrada de comandos
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            String fromUser;
            String fromServer;

            /*
             * Verificamos contra null porque si cierran el buffer recibimos un NULL como lectura
             */
            while ((fromServer = socketInput.readLine()) != null) {
                System.out.println("Server: " + fromServer);
                if (fromServer.equals("Bye")) {
                    break;
                }

                fromUser = userInput.readLine();
                if (fromUser != null) {
                    System.out.println("Client: " + fromUser);
                    socketOutput.println(fromUser);
                }
            }
            userInput.close();
        }
    }
}