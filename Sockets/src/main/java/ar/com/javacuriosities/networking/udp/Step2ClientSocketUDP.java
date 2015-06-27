package ar.com.javacuriosities.networking.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Step2ClientSocketUDP {

	public static void main(String[] args) throws InterruptedException {
		// Definimos el socket cliente
		try (DatagramSocket socket = new DatagramSocket()) {
			DatagramPacket dato = new DatagramPacket("Hello World".getBytes(), // Definimos el array de bytes
					"Hello World".getBytes().length, // Longitud del contenido
					InetAddress.getLocalHost(), // Destinatario
					4000); // Puerto del destinatario

			while (true) {
				socket.send(dato);
				Thread.sleep(2000);

			}
		} catch (Exception e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}
}
