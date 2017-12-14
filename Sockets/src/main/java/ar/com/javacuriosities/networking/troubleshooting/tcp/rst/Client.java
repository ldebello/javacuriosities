package ar.com.javacuriosities.networking.troubleshooting.tcp.rst;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    private static final int PORT = 9300;

    public static void main(String[] args) {
        try (Socket socket = new Socket(InetAddress.getLocalHost(), PORT)) {
            socket.setSoLinger(true, 0);

            String message = "Hello World From Client";
            OutputStream os = socket.getOutputStream();
            for (int i = 0; i <= message.length() - 1; i++) {
                os.write(message.charAt(i));
            }
            os.write(System.lineSeparator().getBytes());
            os.flush();
        } catch (IOException e) {
            // Log and Handle exception
            e.printStackTrace();
        }
    }
}