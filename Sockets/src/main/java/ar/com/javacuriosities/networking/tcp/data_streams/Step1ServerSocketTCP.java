package ar.com.javacuriosities.networking.tcp.data_streams;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Step1ServerSocketTCP {

	public static volatile boolean isRunning = true;

	public static void main(String[] args) {
		try {
			try (ServerSocket server = new ServerSocket(4100)) {
				while (isRunning) {
					try (Socket socket = server.accept()) {
						OutputStream os = socket.getOutputStream();
						DataOutputStream dos = new DataOutputStream(os);

						dos.writeUTF("Hello World DataStream!!!");
					}
				}
			}
		} catch (IOException e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}
}