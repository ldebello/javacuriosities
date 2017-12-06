package ar.com.javacuriosities.networking.tcp.data_streams;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Step2ClientSocketTCP {
    public static void main(String[] args) {
        try (Socket socket = new Socket(InetAddress.getLocalHost(), 4100)) {
            InputStream is = socket.getInputStream();
            DataInputStream dis = new DataInputStream((is));

            String data = dis.readUTF();

            try {
                while (data != null) {
                    System.out.println(data);
                    data = dis.readUTF();
                }
            } catch (EOFException e) {
                // Es importante ver que los DataStreams utilizan EOFException para indicar el EOF en lugar de chequear un valor invalido
                System.out.println("DataStreams EOF");
            }
        } catch (IOException e) {
            // Log and Handle exception
            e.printStackTrace();
        }
    }
}