package ar.com.javacuriosities.networking.troubleshooting.tcp.rst;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int PORT = 9300;

    public static void main(String[] args) {
        try {
            try (ServerSocket server = new ServerSocket(PORT)) {
                Socket socket = server.accept();

                InputStream is = socket.getInputStream();

                int data = is.read();
                while (data != -1) {
                    System.out.print((char) data);
                    data = is.read();
                }
            }
        } catch (IOException e) {
            // Log and Handle exception
            e.printStackTrace();
        }
    }
}
