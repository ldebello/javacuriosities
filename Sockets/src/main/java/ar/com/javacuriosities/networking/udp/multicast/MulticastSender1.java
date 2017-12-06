package ar.com.javacuriosities.networking.udp.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MulticastSender1 {

	public static volatile boolean isRunning = true;

	public static void main(String[] args) {
		// Creamos el MulticastSocket sin especificar ningún puerto
		try (DatagramSocket datagramSocket = new DatagramSocket()) {

			// Buscamos el grupo multicast al cual queremos enviar mensajes
			InetAddress group = InetAddress.getByName("231.0.0.1");

			while (isRunning) {
				// Mensaje
				String mensaje = "Welcome from server 1";
				byte[] data = mensaje.getBytes();

				// Creamos el Datagrama (mensaje, tamaño mensaje, grupo Multicast y puerto):
				DatagramPacket dgp = new DatagramPacket(data, data.length, group, 10000);

				// Enviamos el paquete
				datagramSocket.send(dgp);
			}
		} catch (IOException e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}
}