package ar.com.javacuriosities.networking.tcp.ssl;

import java.io.DataInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;

public class Step2SSLClient {
	public static void main(String[] args) {
		try {
			String trustStoreLocation = new File(ClassLoader.getSystemResource("MyClientTrustStore").toURI()).getCanonicalPath();

			System.setProperty("javax.net.ssl.trustStore", trustStoreLocation);
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
