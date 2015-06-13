package ar.com.javacuriosities.networking.tcp.threading;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Step2ClientSocketTCP {
	public static void main(String[] args) {
		try {
			/*
			 * Intentamos establecer una conexión con el server
			 * Si ejecutamos el cliente antes que el server obtendremos la exception
			 * "java.net.ConnectException: Connection refused"
			 */
			Socket socketToConnect = new Socket(InetAddress.getLocalHost(), 5000);
			
			// Obtenemos el input stream para leer datos
			InputStream is = socketToConnect.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			
			String dataFromServer = new String(dis.readUTF());
			System.out.println(dataFromServer);
			
			// Luego cerramos los stream y el socket
			dis.close();
			is.close();
			socketToConnect.close();
		} catch (IOException e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}
}
