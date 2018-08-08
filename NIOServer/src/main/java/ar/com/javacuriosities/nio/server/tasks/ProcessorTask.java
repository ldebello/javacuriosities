package ar.com.javacuriosities.nio.server.tasks;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.*;
import java.util.concurrent.TimeUnit;

import ar.com.javacuriosities.nio.server.clients.ClientSocket;
import ar.com.javacuriosities.nio.server.message.Message;
import ar.com.javacuriosities.nio.server.message.MessageBuffer;
import ar.com.javacuriosities.nio.server.message.processor.IMessageProcessor;
import ar.com.javacuriosities.nio.server.message.reader.IMessageReaderFactory;
import ar.com.javacuriosities.nio.server.message.writer.MessageWriter;
import ar.com.javacuriosities.nio.server.message.writer.WriteProxy;

/*
 * Esta tarea se encarga de procesar los request en un thread especifico
 */
public class ProcessorTask implements Runnable {

	private Queue<ClientSocket> clientsQueue = null;

	private MessageBuffer readMessageBuffer = null;  
	private IMessageReaderFactory messageReaderFactory = null;

	private Queue<Message> outboundMessageQueue = new LinkedList<>(); 

	private ByteBuffer readByteBuffer = ByteBuffer.allocate(1024 * 1024);
	private ByteBuffer writeByteBuffer = ByteBuffer.allocate(1024 * 1024);
	
	private Selector readSelector = null;
	private Selector writeSelector = null;

	private WriteProxy writeProxy = null;
	private IMessageProcessor messageProcessor = null;

	private long nextSocketId = 16 * 1024;
	private Map<Long, ClientSocket> clientSocketMapId = new HashMap<>();
	
	private Set<ClientSocket> emptyToNonEmptySockets = new HashSet<>();
	private Set<ClientSocket> nonEmptyToEmptySockets = new HashSet<>();

	public ProcessorTask(Queue<ClientSocket> clientsQueue, MessageBuffer readMessageBuffer,
						 MessageBuffer writeMessageBuffer, IMessageReaderFactory messageReaderFactory,
						 IMessageProcessor messageProcessor) throws IOException {
		this.clientsQueue = clientsQueue;

		this.readMessageBuffer = readMessageBuffer;
		
		this.writeProxy = new WriteProxy(writeMessageBuffer, this.outboundMessageQueue);

		this.messageReaderFactory = messageReaderFactory;
		this.messageProcessor = messageProcessor;

		this.readSelector = Selector.open();
		this.writeSelector = Selector.open();
	}

	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			try {
				executeCycle();
			} catch (IOException e) {
				// Log and Handle exception
				e.printStackTrace();
			}

			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				// Log and Handle exception
				e.printStackTrace();
			}
		}
	}

	public void executeCycle() throws IOException {
		takeNewSockets();
		readFromSockets();
		writeToSockets();
	}

	public void takeNewSockets() throws IOException {
		ClientSocket clientSocket = this.clientsQueue.poll();

		while (clientSocket != null) {
			clientSocket.socketId = this.nextSocketId++;
			clientSocket.socketChannel.configureBlocking(false);

			clientSocket.messageReader = this.messageReaderFactory.createMessageReader();
			clientSocket.messageReader.init(this.readMessageBuffer);

			clientSocket.messageWriter = new MessageWriter();

			this.clientSocketMapId.put(clientSocket.socketId, clientSocket);

			SelectionKey key = clientSocket.socketChannel.register(this.readSelector, SelectionKey.OP_READ);
			key.attach(clientSocket);

			clientSocket = this.clientsQueue.poll();
		}
	}

	public void readFromSockets() throws IOException {
		int selectorsReady = this.readSelector.selectNow();

		if (selectorsReady > 0) {
			Set<SelectionKey> selectedKeys = this.readSelector.selectedKeys();
			Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

			while (keyIterator.hasNext()) {
				SelectionKey key = keyIterator.next();

				readFromSocket(key);

				keyIterator.remove();
			}
			selectedKeys.clear();
		}
	}

	public void writeToSockets() throws IOException {
		// Tomamos todos los mensajes de la "outboundMessageQueue"
		takeNewOutboundMessages();

		// Cancelamos todos los sockets que no tienen mas data para escribir
		cancelEmptySockets();

		// Registramos los sockets que tienen data y aun no fueron registrados
		registerNonEmptySockets();

		int writeReady = this.writeSelector.selectNow();

		if (writeReady > 0) {
			Set<SelectionKey> selectionKeys = this.writeSelector.selectedKeys();
			Iterator<SelectionKey> keyIterator = selectionKeys.iterator();

			while (keyIterator.hasNext()) {
				SelectionKey key = keyIterator.next();

				ClientSocket socket = (ClientSocket) key.attachment();

				socket.messageWriter.write(socket, this.writeByteBuffer);

				if (socket.messageWriter.isEmpty()) {
					this.nonEmptyToEmptySockets.add(socket);
				}

				keyIterator.remove();
			}

			selectionKeys.clear();
		}
	}

	private void readFromSocket(SelectionKey key) throws IOException {
		ClientSocket socket = (ClientSocket) key.attachment();
		socket.messageReader.read(socket, this.readByteBuffer);

		List<Message> fullMessages = socket.messageReader.getMessages();
		if (fullMessages.size() > 0) {
			for (Message message : fullMessages) {
				message.socketId = socket.socketId;
				this.messageProcessor.process(message, this.writeProxy);
			}
			fullMessages.clear();
		}

		if (socket.endOfStreamReached) {
			System.out.println("Socket closed: " + socket.socketId);
			this.clientSocketMapId.remove(socket.socketId);
			key.attach(null);
			key.cancel();
			key.channel().close();
		}
	}

	private void registerNonEmptySockets() throws ClosedChannelException {
		for (ClientSocket socket : emptyToNonEmptySockets) {
			socket.socketChannel.register(this.writeSelector, SelectionKey.OP_WRITE, socket);
		}
		emptyToNonEmptySockets.clear();
	}

	private void cancelEmptySockets() {
		for (ClientSocket socket : nonEmptyToEmptySockets) {
			SelectionKey key = socket.socketChannel.keyFor(this.writeSelector);

			key.cancel();
		}
		nonEmptyToEmptySockets.clear();
	}

	private void takeNewOutboundMessages() {
		Message outMessage = this.outboundMessageQueue.poll();
		while (outMessage != null) {
			ClientSocket socket = this.clientSocketMapId.get(outMessage.socketId);

			if (socket != null) {
				MessageWriter messageWriter = socket.messageWriter;
				if (messageWriter.isEmpty()) {
					messageWriter.enqueue(outMessage);
					nonEmptyToEmptySockets.remove(socket);
					emptyToNonEmptySockets.add(socket);
				} else {
					messageWriter.enqueue(outMessage);
				}
			}

			outMessage = this.outboundMessageQueue.poll();
		}
	}
}