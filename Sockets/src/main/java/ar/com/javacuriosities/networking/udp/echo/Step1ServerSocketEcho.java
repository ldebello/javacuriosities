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
public class Step1ServerSocketEcho {

	/*
	 * Puerto UDP al cual se enlaza el servicio
	 * Para poder utilizar algún puerto menor a 1023 debemos tener permiso de root
	 * en caso contrario arrojara una exception "java.net.BindException: Permission denied"
	 */
	public static final int SERVICE_PORT = 1025;

	// Tamaño máximo del paquete, este es un valor común para utilizar como buffer
	public static final int BUFSIZE = 8192;

	// Socket usado para leer y escribir paquetes UDP
	private DatagramSocket socket;

	public Step1ServerSocketEcho() {
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

	public void serviceClients() {
		// Creamos un buffer suficientemente largo para los paquetes entrantes
		byte[] buffer = new byte[BUFSIZE];
		try {
			while (true) {

				// Creamos un DatagramPacket para leer paquetes UDP
				DatagramPacket inputPacket = new DatagramPacket(buffer,
						BUFSIZE);

				// Recibimos los paquetes entrantes
				socket.receive(inputPacket);
				
				System.out.println("Packet coming from "
						+ inputPacket.getAddress() + ":"
						+ inputPacket.getPort() + " length: "
						+ inputPacket.getLength());

				// Volvemos a enviar el mensaje recibido
				socket.send(inputPacket);

			}
		} catch (IOException e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		Step1ServerSocketEcho server = new Step1ServerSocketEcho();
		server.serviceClients();
	}
}