package ar.com.javacuriosities.networking.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Un socket representa un extremo (Endpoint) en una comunicación entre dos programas
 * corriendo en la red.
 * 
 * Dependiendo del protocolo que usemos tendremos distintas capacidades
 * 
 * TCP:
 * - Orientado a la conexión
 * - Garantiza un servicio extremo a extremo fiable
 * 	- Detecta/retransmite segmentos de datos perdidos o erróneos
 * 	- Detecta y descarta segmentos duplicados
 * 	- Ordena los segmentos en el destino y loas pasa de forma ordenada a la capa de aplicación
 * 
 * UDP:
 * - Protocolo de transporte no orientado a la conexión
 * - No garantiza un servicio extremo a extremo fiable
 * - No controla la pérdida de paquetes, los errores o la duplicidad
 * 
 * 
 */
public class Step1ServerSocketTCP {
	public static void main(String[] args) {
		try {
			/*
			 * Creamos un socket el cual va a estar esperando conexiones
			 * Parámetros: Parámetro 1 --> Indica el puerto que vamos a estar
			 * escuchando Parámetro 2 --> Indica el tamaño máximo de la queue de
			 * conexiones Parámetro 3 --> Indica la dirección IP del server
			 * 
			 * Otra opción es crear un socket unbound y luego asociarlo usando
			 * el método bind()
			 */
			try (ServerSocket serverSocket = new ServerSocket(4000, 50,
					InetAddress.getLocalHost())) {

				/*
				 * Este timeout se utiliza para definir el máximo de tiempo a
				 * esperar en el método accept(), el 0 indica infinito, si el
				 * timeout es alcanzado se arroja la exception
				 * "java.net.SocketTimeoutException: Accept timed out"
				 */
				serverSocket.setSoTimeout(0);

				/*
				 * El método accept() es bloqueante por lo cual genera un
				 * bloqueo hasta que llega una conexión
				 */
				while (true) {
					Socket clientSocket = serverSocket.accept();

					// Pedimos el output stream para enviar mensajes al cliente
					OutputStream os = clientSocket.getOutputStream();

					// Usamos un wrapper el cual nos permite escribir valores
					// primitivos de forma simple
					DataOutputStream dos = new DataOutputStream(os);

					// Write message
					dos.writeUTF("Hi!!!");

					// Close output stream and client
					dos.close();
					os.close();
					clientSocket.close();
				}
			}
		} catch (IOException e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}
}
