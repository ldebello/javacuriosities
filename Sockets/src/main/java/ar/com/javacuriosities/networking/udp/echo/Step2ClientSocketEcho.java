package ar.com.javacuriosities.networking.udp.echo;

import java.net.*;
import java.io.*;

public class Step2ClientSocketEcho {
    
    // Puerto UDP al cual se enlaza el servicio
    public static final int SERVICE_PORT = 1025;
    
    /*
     * El tamaño  máximo del paquete del paquete puede variar pero para
     * comunicación óptima se sugiere que sea  el MSS (Maximum Segment Size)
     * MSS: Tamaño máximo de segmento, este se calcula (MTU - Size Cabecera UDP/TCP - Cabecera IP)
     * 
     * MTU (Maximum Transmission Unit): Los valores típicos son 576 o 1500
     * Cabecera UDP: 8 + Payload (Datos)
     * Cabecera TCP: 20 + Payload (Datos)
     * Cabecera IP: Usualmente 20 hasta 60 por encabezados adicionales
     * 
     * Size: 1500 - 60 - 8
     *  
     * El valor calculado arriba es un valor con el cual podremos confiar que no tendremos
     * fragmentación (La fragmentación es maneja por el protocolo IP), esto incrementa nuestras chances de recibir el paquete, dado que si 
     * el size es mayor que nuestro MTU se produce la fragmentación y si un fragmento es perdido todo el paquete será perdido.
     */
    public static final int BUFSIZE = 1432;

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
                byte[] sendBuffer = new byte[BUFSIZE];
                
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
                byte[] recbuf = new byte[BUFSIZE];
                DatagramPacket receivedPacket = new DatagramPacket(recbuf, BUFSIZE);
                
                
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