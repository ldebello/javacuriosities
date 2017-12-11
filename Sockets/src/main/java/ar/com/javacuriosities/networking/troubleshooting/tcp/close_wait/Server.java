package ar.com.javacuriosities.networking.troubleshooting.tcp.close_wait;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 */
public class Server {

    private static final int PORT = 9100;

    public static volatile boolean isRunning = true;

    public static void main(String[] args) {
        try {
            try (ServerSocket server = new ServerSocket(PORT)) {
                while (isRunning) {
                    Socket socket = server.accept();

                    OutputStream os = socket.getOutputStream();
                    DataOutputStream dos = new DataOutputStream(os);

                    dos.writeUTF("CloseWait Issue");
                }
            }
        } catch (IOException e) {
            // Log and Handle exception
            e.printStackTrace();
        }
    }
}
