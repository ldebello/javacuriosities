package ar.com.javacuriosities.networking.udp.multicast.receivers;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;


public class MulticastReceiver2 {

    public static volatile boolean isRunning = true;

    public static void main(String[] args) {
        MulticastSocket multicastSocket = null;

        InetAddress group = null;
        try {
            // Creamos un socket multicast en el puerto 10000:
            multicastSocket = new MulticastSocket(10000);

            // Configuramos el grupo (IP) a la que nos conectaremos:
            group = InetAddress.getByName("231.0.0.1");

            // Nos unimos al grupo
            multicastSocket.joinGroup(group);

            while (isRunning) {
                // Recibimos el paquete del socket
                byte[] buffer = new byte[100];

                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

                multicastSocket.receive(packet);

                System.out.println(new String(packet.getData(), 0, packet.getLength()));

                System.out.println("Receiver 2 - Remote Port:" + packet.getPort());
                System.out.println("Receiver 2 - Remote Address:" + packet.getAddress());
                System.out.println("Receiver 2 - Remote Socket Address:" + packet.getSocketAddress());
            }
        } catch (IOException e) {
            // Log and Handle exception
            e.printStackTrace();
        } finally {
            // Salimos de los grupo multicast
            try {
                multicastSocket.leaveGroup(group);
            } catch (IOException e) {
                // Log and Handle exception
                e.printStackTrace();
            }

            // Cerramos el socket:
            multicastSocket.close();
        }
    }
}