package ar.com.javacuriosities.networking.troubleshooting.tcp.time_wait;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * El estado TIME_WAIT es uno que obtiene el socket que realizo el active close, y es un estado en el
 * cual el socket va a permanecer un determinado tiempo, generalmente el doble de tiempo del MSL (Maximum Segment Lifetime).
 * Esto en si no seria un problema porque es parte del flujo normal, pero dado que si quisiéramos reutilizar ese puerto
 * no podríamos por un tiempo, entonces se aconseja que usualmente sea el client quien inicia el active close.
 *
 * Solución:
 * - La mas recomendada cambiar nuestro flujo para que el client sea quien inicia el active close.
 * - Otra alternativa es utilizar el método setSoLinger(), aunque es mas como un workaround que una solución final.
 */
public class Server {

    private static final int PORT = 9200;

    public static volatile boolean isRunning = true;

    public static void main(String[] args) {
        try {
            try (ServerSocket server = new ServerSocket(PORT)) {
                while (isRunning) {
                    try (Socket socket = server.accept()) {
                        OutputStream os = socket.getOutputStream();
                        DataOutputStream dos = new DataOutputStream(os);

                        dos.writeUTF("TimeWait Example");

                        /* Configuramos el tiempo que estaremos en TIME_WAIT el maximo valor depende del SO.
                         * Podemos verificar si este flag esta activado por medio de getSoLinger(), si retorna -1 significa
                         * que no esta disponible.
                         */
                        socket.setSoLinger(true, 60);

                        dos.close();
                    }
                }
            }
        } catch (IOException e) {
            // Log and Handle exception
            e.printStackTrace();
        }
    }
}
