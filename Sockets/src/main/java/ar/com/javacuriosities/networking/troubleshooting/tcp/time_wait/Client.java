package ar.com.javacuriosities.networking.troubleshooting.tcp.time_wait;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    private static final int PORT = 9200;
    private static final int CONNECTIONS = 100;

    public static void main(String[] args) {
        for (int connectionId = 0; connectionId < CONNECTIONS; connectionId++) {
            try (Socket socket = new Socket(InetAddress.getLocalHost(), PORT)) {
                InputStream is = socket.getInputStream();
                DataInputStream dis = new DataInputStream(is);

                System.out.println("Time Wait - Connection #" + connectionId + " - Received Message: " + dis.readUTF());

                dis.close();
            } catch (IOException e) {
                // Log and Handle exception
                e.printStackTrace();
            }
        }
    }
}