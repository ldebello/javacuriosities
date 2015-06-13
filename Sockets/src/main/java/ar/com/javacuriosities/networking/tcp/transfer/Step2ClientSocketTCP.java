package ar.com.javacuriosities.networking.tcp.transfer;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Step2ClientSocketTCP {

	public static void main(String[] args) {

		try (Socket socketToConnet = new Socket(InetAddress.getLocalHost(), 9500)){
			System.out.println("Connecting...");

			// receive file
			byte[] bytes = new byte[5];
			InputStream is = socketToConnet.getInputStream();
			
			FileOutputStream fos = new FileOutputStream("data_backup.txt");
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			
			int bytesRead = 0;
			while ((bytesRead = is.read(bytes)) != -1) {
				System.out.println("Receiving file (" + bytesRead + " bytes)");
				bos.write(bytes, 0, bytesRead);
			}
				
			bos.close();
		} catch (UnknownHostException e) {
			// Log and Handle exception
			e.printStackTrace();
		} catch (IOException e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}
}
