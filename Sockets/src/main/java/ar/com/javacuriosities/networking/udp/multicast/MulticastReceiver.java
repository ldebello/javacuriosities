package ar.com.javacuriosities.networking.udp.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/*
 * Existen un rango de IP que son las pertenecientes a las IP clase D, estas se encuentran
 * en el rango 224.0.0.0 - 239.255.255.255.
 * Si el emisor envía un datagrama a esta dirección, se harán multiples copias para todos los interesados
 */
public class MulticastReceiver {

	public static volatile boolean isRunning = true;

	public static void main(String[] args) {
		MulticastSocket multicastSocket = null;

		InetAddress group1 = null;
		InetAddress group2 = null;
		try {
			// Creamos un socket multicast en el puerto 10000:
			multicastSocket = new MulticastSocket(10000);
			
			// Configuramos el grupo (IP) a la que nos conectaremos:
			group1 = InetAddress.getByName("231.0.0.1");
			group2 = InetAddress.getByName("231.0.0.2");

			// Nos unimos al grupo: (No podemos unirnos dos veces al mismo grupo)
			multicastSocket.joinGroup(group1);
			multicastSocket.joinGroup(group2);

			while (isRunning) {
				// Recibimos el paquete del socket
				byte[] buffer = new byte[100];

				DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

				multicastSocket.receive(packet);

				System.out.println(new String(packet.getData(), 0, packet.getLength()));

				System.out.println("Remote Port:" + packet.getPort());
				System.out.println("Remote Address:" + packet.getAddress());
				System.out.println("Remote Socket Address:" + packet.getSocketAddress());
			}
		} catch (IOException e) {
			// Log and Handle exception
			e.printStackTrace();
		} finally {
			// Salimos de los grupo multicast
			try {
				multicastSocket.leaveGroup(group1);
				multicastSocket.leaveGroup(group2);
			} catch (IOException e) {
				// Log and Handle exception
				e.printStackTrace();
			}

			// Cerramos el socket:
			multicastSocket.close();
		}
	}
}