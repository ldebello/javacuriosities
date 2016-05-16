package ar.com.javacuriosities.nio.server.tasks;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Queue;

import ar.com.javacuriosities.nio.server.clients.ClientSocket;

/*
 * Esta tarea corre en un thread separado aceptando conexiones
 */
public class AccepterTask implements Runnable {

	private int port = 0;
	private ServerSocketChannel serverSocket = null;

	private Queue<ClientSocket> clientSocketQueue = null;

	public AccepterTask(Queue<ClientSocket> clientSocketQueue, int port) {
		this.port = port;
		this.clientSocketQueue = clientSocketQueue;
	}

	public void run() {
		try {
			this.serverSocket = ServerSocketChannel.open();
			this.serverSocket.bind(new InetSocketAddress(port));
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		while (true) {
			try {
				SocketChannel channel = this.serverSocket.accept();
				
				System.out.println("Client Socket accepted: " + channel);

				// TODO Deberíamos chequear si la queue acepta mas conexiones
				clientSocketQueue.add(new ClientSocket(channel));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}