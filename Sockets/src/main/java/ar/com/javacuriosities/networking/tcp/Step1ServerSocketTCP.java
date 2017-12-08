package ar.com.javacuriosities.networking.tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Un socket representa un extremo (endpoint) en una comunicación entre dos programas corriendo en una red.
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
 * TC States:
 * Cuando utilizamos el protocolo TCP los sockets tienen distintos estados, y las operaciones pueden ser activas o pasivas.
 *
 * - CLOSED: No hay conexión.
 * - LISTEN: El endpoint local esta esperando por una conexión de un endpoint remoto. i.e. Un open pasivo fue ejecutado.
 * - SYN-SENT: El primer step del 3-Way Handshake fue realizado, un pedido de conexión fue enviado al remote endpoint. i.e. Un open activo fue ejecutado.
 *
 * 3-Way Handshake:
 * Antes de establecer una conexión con un cliente, debemos ejecutar el proceso de Handshake el cual se encarga de esto mismo enviando/recibiendo
 * ciertos paquetes. Cada paquete del protocolo TCP puede incluir alguno de sus 6 flags
 *
 * SYN (Synchronize), ACK (Acknowledgement), RST (Reset), PSH (Push), URG (Urgent) y FIN (Urgent)
 *
 * 3-Way Handshake Steps:
 * Step 1:
 * Aplicación A --> Envía un paquete con el flag SYN al puerto X de la aplicación B con un numero de secuencia
 * 
 * A ------ [SYN secuencia:X] ----------------> B
 * 
 * Step 2:
 * Si el puerto esta abierto la aplicación B responde con los flag SYN y ACK también define un numero de secuencia inicial y envía el ACK con el
 * numero de secuencia de A mas 1. Si el puerto esta cerrado nos responden con RST y ACK.
 * 
 * B ------ [SYN+ACK secuencia:Y ack:X+1] ----> A
 * 
 * Step 3:
 * La aplicación responde con un tercer paquete para confirmar la conexión con otro flag ACK
 * 
 * A ------ [ACK secuencia:X+1 ack:Y+1] ------> B
 * 
 */
public class Step1ServerSocketTCP {

	public static volatile boolean isRunning = true;

	public static void main(String[] args) {
		try {
			/*
			 * Creamos un socket el cual va a estar esperando conexiones
			 * Parámetros:
			 *
			 * Parámetro 1 --> Indica el puerto que vamos a estar escuchando.
			 * Parámetro 2 --> Indica el tamaño máximo de la queue de conexiones.
			 * Parámetro 3 --> Indica la dirección IP del server.
			 * 
			 * Otra opción es crear un socket unbound y luego asociarlo usando
			 * el método bind()
			 */
			try (ServerSocket server = new ServerSocket(4000, 50,
					InetAddress.getLocalHost())) {

				/*
				 * Este timeout se utiliza para definir el máximo de tiempo a
				 * esperar en el método accept(), el 0 indica infinito, si el
				 * timeout es alcanzado se arroja la exception
				 * "java.net.SocketTimeoutException: Accept timed out"
				 */
				server.setSoTimeout(0);

				/*
				 * El método accept() es bloqueante por lo cual genera un
				 * bloqueo hasta que llega una conexión
				 */
				while (isRunning) {
					try (Socket socket = server.accept()) {

						/**
						 * Por medio de este método podemos desactivar el Nagle's Algorithm. Que controla el envió de datos
						 * para poder enviar segmentos completos por medio de esperar los writes necesarios para llenar los
						 * segmentos.
						 */
						socket.setTcpNoDelay(true);

						// Pedimos el output stream para enviar mensajes al cliente
						OutputStream os = socket.getOutputStream();

						os.write('h');
						os.write('e');
						os.write('l');
						os.write('l');
						os.write('o');
					}
				}
			}
		} catch (IOException e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}
}