package ar.com.javacuriosities.networking.tcp.ssl;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;

public class Step2SSLClient {
	public static void main(String[] args) {
		try {
			System.setProperty("javax.net.ssl.trustStore", "MyClientTrustStore");
			System.setProperty("javax.net.ssl.trustStorePassword", "123456");
			
			SocketFactory socketFactory = SSLSocketFactory.getDefault();
			
			try (Socket socket = socketFactory.createSocket(InetAddress.getLocalHost(), 4000)) {
				// Obtenemos el input stream para leer datos
				InputStream is = socket.getInputStream();
				DataInputStream dis = new DataInputStream(is);

				System.out.println(dis.readUTF());

				// Cerramos el input stream
				dis.close();
			}
		} catch (Exception e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}
}
