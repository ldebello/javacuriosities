package ar.com.javacuriosities.networking.tcp.serialization;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Step2ClientSocketTCP {

	public static void main(String[] args) {
		try (Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 6000)) {

			// Solicitamos el input stream para leer el objeto desde el server
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

			// Leemos el objeto desde la fuente de datos
			Person datos = (Person) ois.readObject();

			// Imprimimos el objeto
			System.out.println(datos);
		} catch (ClassNotFoundException e) {
			// Log and Handle exception
			e.printStackTrace();
		} catch (IOException e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}
}