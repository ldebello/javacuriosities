package ar.com.javacuriosities.networking.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;

/*
 * Este ejemplo lee datos desde el server, otra opción es
 * enviar datos al server por lo cual en lugar de obtener el InputStream
 * debemos obtener el OutputStream para escribir en el.
 */
public class Step2ClientSocketTCP {
    public static void main(String[] args) {
		/*
         * Intentamos establecer una conexión con el server
         * Si ejecutamos el cliente antes que el server obtendremos la exception
         * "java.net.ConnectException: Connection refused"
         */
        try (Socket socket = new Socket(InetAddress.getLocalHost(), 4000)) {
            // Obtenemos el input stream para leer datos
            InputStream is = socket.getInputStream();

            int value = is.read();
            while (value != -1) {
                System.out.print((char) value);
                value = is.read();
            }
        } catch (IOException e) {
            // Log and Handle exception
            e.printStackTrace();
        }
    }
}