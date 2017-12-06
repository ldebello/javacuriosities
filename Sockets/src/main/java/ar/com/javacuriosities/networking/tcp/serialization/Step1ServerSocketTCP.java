package ar.com.javacuriosities.networking.tcp.serialization;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Step1ServerSocketTCP {

	public static volatile boolean isRunning = true;

	public static void main(String[] args) {
		try (ServerSocket server = new ServerSocket(6000)) {
			while (isRunning) {
				System.out.println("Waiting for client ....");
				try (Socket socket = server.accept()) {
					System.out.println("Client accepted: " + socket.getInetAddress());

					// Pedimos el output stream y lo decoramos con un object output stream
					ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

					// Creamos el objeto a enviar
					Person datos = new Person();
					datos.setName("Cosme Fulanito");
					datos.setAge(99);
					datos.setHeight(1.83);

					// Enviamos el objeto
					oos.writeObject(datos);
					oos.flush();

					oos.close();
				}
			}
		} catch (IOException e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}
}
