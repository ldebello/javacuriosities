package ar.com.javacuriosities.networking.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

public class Step2SenderSocketUDP {

	public static volatile boolean isRunning = true;

	public static void main(String[] args) throws InterruptedException {
		// Definimos el socket cliente
		try (DatagramSocket socket = new DatagramSocket()) {
			String message = "Hello World";
			byte[] buffer = message.getBytes();

			DatagramPacket packet = new DatagramPacket(buffer, // Definimos el array de bytes
					buffer.length, // Longitud del contenido
					InetAddress.getLocalHost(), // Destinatario
					4000); // Puerto del destinatario

			while (isRunning) {
				socket.send(packet);
				socket.send(packet);
				Thread.sleep(TimeUnit.SECONDS.toMillis(2));
			}
		} catch (Exception e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}
}
