package ar.com.javacuriosities.networking.troubleshooting.tcp.close_wait;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    private static int MAX_CLIENTS = 1000;

    public static void main(String[] args) {
        for (int clientId = 0; clientId < MAX_CLIENTS; clientId++) {
            try (Socket socket = new Socket(InetAddress.getLocalHost(), 9100)) {
                InputStream is = socket.getInputStream();
                DataInputStream dis = new DataInputStream(is);

                System.out.println("Client #" + clientId + " - Received Message: " + dis.readUTF());
            } catch (IOException e) {
                // Log and Handle exception
                e.printStackTrace();
            }
        }
    }
}