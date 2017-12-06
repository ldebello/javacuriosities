package ar.com.javacuriosities.networking.tcp.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Step1ServerSocketHandlerTCP {

    public static void main(String[] args) {
        // Creamos el Server Socket
        try (ServerSocket server = new ServerSocket(4444)) {

            // Aceptamos peticiones
            try (Socket socket = server.accept()) {
                // Input y Output
                PrintWriter socketOutput = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                // Variables auxiliares
                String inputLine, outputLine;

                /*
                 * Usamos un handler que responde en base al mensaje recibido
                 */
                ServerSocketInputHandler handler = new ServerSocketInputHandler();
                outputLine = handler.processInput(null);
                socketOutput.println(outputLine);

                /*
                 * Verificamos contra null porque si cierran el buffer recibimos un
                 * NULL como lectura
                 */
                while ((inputLine = socketInput.readLine()) != null) {
                    outputLine = handler.processInput(inputLine);
                    socketOutput.println(outputLine);
                    if (outputLine.equals("Bye")) {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            // Log and Handle exception
            e.printStackTrace();
        }
    }
}