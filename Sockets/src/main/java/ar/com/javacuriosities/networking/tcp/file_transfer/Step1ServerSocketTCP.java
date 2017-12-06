package ar.com.javacuriosities.networking.tcp.file_transfer;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Step1ServerSocketTCP {

	public static volatile boolean isRunning = true;

	public static void main(String[] args) {
		try (ServerSocket server = new ServerSocket(9500)) {
			while (isRunning) {
				System.out.println("Waiting client...");
				
				try (Socket socket = server.accept()) {
					System.out.println("Accepted connection : " + socket.getInetAddress());

					// Creamos un array de bytes para ir leyendo el file, acá creamos un array pequeño para el ejemplo
					byte[] bytes = new byte[10];

					// Pedimos el output stream
					OutputStream os = socket.getOutputStream();

					InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("data.txt");
					BufferedInputStream bis = new BufferedInputStream(resourceAsStream);

					int bytesRead = 0;
					while ((bytesRead = bis.read(bytes)) != -1) {
						System.out.println("Sending file (" + bytesRead + " bytes)");
						os.write(bytes, 0, bytesRead);
					}

					bis.close();
					System.out.println("Done.");
				}
			}
		} catch (IOException e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}
}