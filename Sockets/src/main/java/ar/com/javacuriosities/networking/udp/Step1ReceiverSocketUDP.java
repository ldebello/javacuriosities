package ar.com.javacuriosities.networking.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
 * El protocolo UDP esta soportado por dos clases principales
 * DatagramSocket --> La cual esta encargada de enviar y recibir datos
 * DatagramPacket --> Contiene la información a enviar junto con los datos del destinarlo, el encabezado posee
 * Source Port|Destination Port|Length|UDP Checksum|Data
 * 
 */
public class Step1ReceiverSocketUDP {

	public static volatile boolean isRunning = true;

	public static void main(String[] args) throws InterruptedException {
		// Definimos un socket
		try (DatagramSocket socket = new DatagramSocket(4000, InetAddress.getLocalHost())){
			DatagramPacket packet = new DatagramPacket(new byte[100], 100);

			// Podemos asignar un timeout para el método receive
			// socket.setSoTimeout(1000);

			/*
			 *  Aunque el protocolo UDP no es orientado a la conexión podemos usar
			 *  el método connect() para restringir la llegada de paquetes desde una
			 *  IP + Port.
			 *  Si ejecutamos el método connect() varias veces solo queda restringido
			 *  al ultimo asignado
			 */
			// socket.connect(InetAddress.getLocalHost(), 5000);
			while (isRunning) {
				socket.receive(packet);

				System.out.println("Remote Port:" + packet.getPort());
				System.out.println("Remote Address:" + packet.getAddress());
				System.out.println("Remote Socket Address:" + packet.getSocketAddress());

				System.out.println("Message: " + new String(packet.getData(), 0, packet.getLength()));
			}
		} catch (Exception e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}
}
