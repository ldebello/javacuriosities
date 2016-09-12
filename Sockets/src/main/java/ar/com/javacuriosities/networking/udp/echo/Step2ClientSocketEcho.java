package ar.com.javacuriosities.networking.udp.echo;

import java.net.*;
import java.io.*;

public class Step2ClientSocketEcho {
    
    // Puerto UDP al cual se enlaza el servicio
    public static final int SERVICE_PORT = 1025;
    
    public static final int BUFFER_SIZE = 1500;

    public static void main(String args[]) {
        InetAddress inetAddress = null;
        String hostname = "localhost";
        
        try {
            // Resolvemos el nombre de host a una dirección IP
            inetAddress = InetAddress.getByName(hostname);
            
            // Nos enlazamos a cualquier puerto libre
            DatagramSocket socket = new DatagramSocket();
            
            // Establecemos un valor de timeout de 2 segundos
            socket.setSoTimeout(2 * 1000);
            
            // Enviamos 10 paquetes
            for (int i = 1; i <= 10; i++) {
                // Copiamos algunos datos a nuestro paquete
                String message = "Number of passage " + i;
                char[] contentAsArray = message.toCharArray();
                byte[] sendBuffer = new byte[BUFFER_SIZE];
                
                for (int offset = 0; offset < contentAsArray.length; offset++) {
                    sendBuffer[offset] = (byte) contentAsArray[offset];
                }
                
                // Creamos un paquete para enviar al servidor UDP
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, contentAsArray.length, inetAddress, SERVICE_PORT);
                System.out.println("Sending package to: " + hostname);
                
                // Enviamos el paquete
                socket.send(sendPacket);
                System.out.println("Waiting for package");
                
                // Creamos un pequeño paquete para recibir paquetes UDP 
                byte[] recbuf = new byte[BUFFER_SIZE];
                DatagramPacket receivedPacket = new DatagramPacket(recbuf, BUFFER_SIZE);
                
                
                boolean timeout = false;
                
                /*
                 * Capturamos cualquier InterruptedIOException lanzada mientras se espera por el paquete UDP
                 */
                try {
                    socket.receive(receivedPacket);
                } catch (InterruptedIOException ioe) {
                    timeout = true;
                }
                
                if (!timeout) {
                    System.out.println("Processing package!");
                    System.out.println("Details : " + receivedPacket.getAddress());

                    // Obtenemos un stream de bytes de entrada para leer el paquete UDP
                    ByteArrayInputStream bin = new ByteArrayInputStream(receivedPacket.getData(), 0, receivedPacket.getLength());
                    
                    // Creamos un buffer para acelerar la lectura
                    BufferedReader reader = new BufferedReader(new InputStreamReader(bin));
                    
                    // Bucle infinito
                    while(true) {
                        String line = reader.readLine();
                        // verificar fin de datos
                        if (line == null) {
                            break;
                        } else {
                            System.out.println(line);
                        }
                    }
                } else {
                    System.out.println("Packet lost!");
                }
                
                try {
                    // Esperamos antes de enviar el siguiente paquete
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                }
            }
            socket.close();
        } catch (IOException e) {
        	// Log and Handle exception
        	e.printStackTrace();
        }
    }
}