package ar.com.javacuriosities.networking.tcp.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Step1ServerSocketHandlerTCP {
	public static void main(String[] args) {
		try {
			// Creamos el Server Socket
			ServerSocket serverSocket = new ServerSocket(4444);

			// Aceptamos peticiones
			Socket clientSocket = serverSocket.accept();

			// Input y Output
			PrintWriter clientSocketOutput = new PrintWriter(
					clientSocket.getOutputStream(), true);
			BufferedReader clientSocketInput = new BufferedReader(
					new InputStreamReader(clientSocket.getInputStream()));

			// Variables auxiliares
			String inputLine, outputLine;

			/*
			 * Usamos un handler que responde en base al mensaje recibido
			 */
			ServerSocketInputHandler handler = new ServerSocketInputHandler();
			outputLine = handler.processInput(null);
			clientSocketOutput.println(outputLine);

			/*
			 * Verificamos contra null porque si cierran el buffer recibimos un
			 * NULL como lectura
			 */
			while ((inputLine = clientSocketInput.readLine()) != null) {
				outputLine = handler.processInput(inputLine);
				clientSocketOutput.println(outputLine);
				if (outputLine.equals("Bye")) {
					break;
				}
			}

			clientSocketOutput.close();
			clientSocketInput.close();
			clientSocket.close();
			serverSocket.close();
		} catch (IOException e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}
}