package ar.com.javacuriosities.networking.udp.echo;

import java.net.*;
import java.io.*;

/*
 * En general el servicio echo esta implementado usando el protocolo UDP
 * dado que su mayor velocidad lo vuelve ideal para esto.
 * 
 * Si queremos profundizar mas sobre el tema podemos acudir a su RFC, en la
 * siguiente página se encuentran muchas RFC traducidas al español
 * http://www.rfc-es.org/index.php
 */
public class Step1ReceiverSocketEcho {

	public static volatile boolean isRunning = true;

	/*
	 * Puerto UDP al cual se enlaza el servicio
	 * en caso contrario arrojara una exception "java.net.BindException: Permission denied"
	 */
	public static final int SERVICE_PORT = 1025;

	// Tamaño máximo del paquete, este es un valor común para utilizar como buffer
	public static final int BUFFER_SIZE = 8192;

	// Socket usado para leer y escribir paquetes UDP
	private DatagramSocket socket;

	public Step1ReceiverSocketEcho() {
		try {
			// Enlazarse al puerto UDP especificado para escuchar paquetes de
			// datos entrantes
			socket = new DatagramSocket(SERVICE_PORT);
			System.out.println("Service started at " + socket.getLocalPort());
		} catch (Exception e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}

	public void startEchoService() {
		// Creamos un buffer suficientemente largo para los paquetes entrantes
		byte[] buffer = new byte[BUFFER_SIZE];
		try {
			while (isRunning) {

				// Creamos un DatagramPacket para leer paquetes UDP
				DatagramPacket packet = new DatagramPacket(buffer, BUFFER_SIZE);

				// Recibimos los paquetes entrantes
				socket.receive(packet);
				
				System.out.println("Packet coming from "
						+ packet.getAddress() + ":"
						+ packet.getPort() + " length: "
						+ packet.getLength());

				// Volvemos a enviar el mensaje recibido
				socket.send(packet);
			}
		} catch (IOException e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		Step1ReceiverSocketEcho receiver = new Step1ReceiverSocketEcho();
		receiver.startEchoService();
	}
}