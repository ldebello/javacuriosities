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

	private ServerSocketChannel serverChannel = null;

	private Queue<ClientSocket> clientsQueue = null;

	public AccepterTask(Queue<ClientSocket> clientsQueue, int port) {
		this.port = port;
		this.clientsQueue = clientsQueue;
	}

	public void run() {
		try {
			this.serverChannel = ServerSocketChannel.open();
			this.serverChannel.bind(new InetSocketAddress(port));
		} catch (IOException e) {
			// Log and Handle exception
			e.printStackTrace();
			return;
		}

		while (!Thread.currentThread().isInterrupted()) {
			try {
				SocketChannel channel = this.serverChannel.accept();
				
				System.out.println("Client Socket accepted: " + channel);

				// TODO: Deberíamos chequear si la queue acepta mas conexiones
				clientsQueue.add(new ClientSocket(channel));
			} catch (IOException e) {
				// Log and Handle exception
				e.printStackTrace();
			}
		}
	}
}