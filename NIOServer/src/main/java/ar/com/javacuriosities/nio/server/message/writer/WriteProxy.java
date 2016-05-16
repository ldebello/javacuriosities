package ar.com.javacuriosities.nio.server.message.writer;

import java.util.Queue;

import ar.com.javacuriosities.nio.server.message.Message;
import ar.com.javacuriosities.nio.server.message.MessageBuffer;

/*
 * Nos permite obtener el mensaje de este request y encolar un response apropiado
 */
public class WriteProxy {

	private MessageBuffer messageBuffer = null;
	private Queue<Message> writeQueue = null;

	public WriteProxy(MessageBuffer messageBuffer, Queue<Message> writeQueue) {
		this.messageBuffer = messageBuffer;
		this.writeQueue = writeQueue;
	}

	public Message getMessage() {
		return this.messageBuffer.getMessage();
	}

	public boolean enqueue(Message message) {
		return this.writeQueue.offer(message);
	}
}