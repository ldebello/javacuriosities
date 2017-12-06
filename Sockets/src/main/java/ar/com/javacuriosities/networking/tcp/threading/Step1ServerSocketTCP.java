package ar.com.javacuriosities.networking.tcp.threading;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * Cuando queremos manejar multiples clientes de forma concurrente debemos
 * podremos usar Threads
 * 
 */
public class Step1ServerSocketTCP {

	public static volatile boolean isRunning = true;

	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newFixedThreadPool(2);
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

			try (ServerSocket serverSocket = new ServerSocket(5000, 50,
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
				while (isRunning) {
					// Aquí podremos utilizar un thread pool utilizando el concurrency package agregado en 1.5 o ir creando nuestros propios hilos
					threadPool.execute(new ClientHandler(serverSocket.accept()));
					
					// Thread thread = new Thread(new ClientHandler(serverSocket.accept()));
					// thread.start();
				}
			}
		} catch (IOException e) {
			// Log and Handle exception
			e.printStackTrace();
		} finally {
			threadPool.shutdown();
		}
	}

	private static final class ClientHandler implements Runnable {

		private Socket socket;

		public ClientHandler(Socket socket) {
			super();
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				// Pedimos el output stream para enviar mensajes al cliente
				OutputStream os = socket.getOutputStream();

				// Usamos un wrapper el cual nos permite escribir valores
				// primitivos de forma simple
				DataOutputStream dos = new DataOutputStream(os);

				// Write message
				dos.writeUTF("Hi!!!");

				// Close output stream and client
				dos.close();
				socket.close();
			} catch (IOException e) {
				// Log and Handle exception
				e.printStackTrace();
			}
		}
	}
}