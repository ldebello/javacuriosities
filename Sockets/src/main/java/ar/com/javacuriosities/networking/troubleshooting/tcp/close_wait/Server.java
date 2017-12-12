package ar.com.javacuriosities.networking.troubleshooting.tcp.close_wait;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * El estado de CLOSE_WAIT indica que el socket esta esperando active close desde la aplicación. Un socket en este estado
 * puede permanecer indefinidamente, lo cual puede generar un file descriptor leak (En Java esto se manifiesta como "Too many open files" error)
 *
 * Una alternativa para ver el estado de los sockets es usar el comando netstat
 *
 * netstat -an | grep CLOSE_WAIT
 *
 * Solución:
 * Para solucionar esto ambos lados tiene que ejecutar el método close.
 */
public class Server {

    private static final int PORT = 9100;

    public static volatile boolean isRunning = true;

    public static void main(String[] args) {
        try {
            try (ServerSocket server = new ServerSocket(PORT)) {
                while (isRunning) {
                    // Este socket queda en estado de CLOSE_WAIT porque no estamos invocando al close
                    Socket socket = server.accept();

                    OutputStream os = socket.getOutputStream();
                    DataOutputStream dos = new DataOutputStream(os);

                    dos.writeUTF("CloseWait Issue");

                    dos.close();
                }
            }
        } catch (IOException e) {
            // Log and Handle exception
            e.printStackTrace();
        }
    }
}
