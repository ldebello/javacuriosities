package ar.com.javacuriosities.networking.tcp.webserver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Por medio de este WebServer básico vamos a poder entrar a un browser
 * y al conectarnos a http://localhost:8084/index.html, esa petición
 * Será manejada por nuestro server
 */
public class WebServer {

	public static volatile boolean isRunning = true;

	public static void main(String args[]) {
		WebServer webServer = new WebServer();
		webServer.start();
	}

	private void start() {
		System.out.println("WebServer started port 8084");

		try (ServerSocket serverSocket = new ServerSocket(8084)) {

			System.out.println("Waiting connections");
			while (isRunning) {
				// Esperando la conexión
				try (Socket client = serverSocket.accept()) {
					// Cliente conectado
					System.out.println("Connection accepted, sending data");

					BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
					PrintWriter output = new PrintWriter(client.getOutputStream());

					// Leemos los datos recibidos desde el cliente, serían los encabezados de la petición
					String data = null;
					while (!"".equals(data)) {
						data = input.readLine();
						System.out.println(data);
					}

					// Enviamos la respuesta

					// Enviamos la cabecera
					output.println("HTTP/1.1 200 OK");
					output.println("Content-Type: text/html");
					output.println("Server: JavaCuriosites");

					// Esta linea en blanco indica que terminaron los header
					output.println("");

					// Enviamos la pagina en HTML
					output.println("<html>");
					output.println("<head>");
					output.println("<title>JavaCuriosites WebServer</title>");
					output.println("</head>");
					output.println("<body>");
					output.println("<h1>Welcome to our mini Web-Server</h1>");
					output.println("</body>");
					output.println("</html>");

					output.close();
				}
			}
		} catch (Exception e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}
}