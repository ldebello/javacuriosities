package ar.com.javacuriosities.nio.server.message;

/*
 * Creamos un shared buffer el cual puede contener multiples mensajes. Un mensaje obtiene una sección del buffer 
 * para usar y si este es mas grande es movido a una sección mayor.
 *
 */
public class MessageBuffer {

	private static int KB = 1024;
	private static int MB = 1024 * KB;

	private static final int CAPACITY_SMALL = 4 * KB;
	private static final int CAPACITY_MEDIUM = 128 * KB;
	private static final int CAPACITY_LARGE = 1024 * KB;

	private byte[] smallMessageBuffer = new byte[1024 * 4 * KB]; // 1024 x 4KB mensajes = 4MB
	private byte[] mediumMessageBuffer = new byte[128 * 128 * KB]; // 128 x 128KB mensajes = 16MB
	private byte[] largeMessageBuffer = new byte[16 * 1 * MB]; // 16 * 1MB mensajes = 16MB

	private QueueIntFlip smallMessageBufferFreeBlocks = new QueueIntFlip(1024); // 1024 secciones
	private QueueIntFlip mediumMessageBufferFreeBlocks = new QueueIntFlip(128); // 128 secciones
	private QueueIntFlip largeMessageBufferFreeBlocks = new QueueIntFlip(16); // 16 secciones

	public MessageBuffer() {
		// Inicializamos todas Queue de mensajes para las secciones
		for (int i = 0; i < smallMessageBuffer.length; i += CAPACITY_SMALL) {
			this.smallMessageBufferFreeBlocks.put(i);
		}
		for (int i = 0; i < mediumMessageBuffer.length; i += CAPACITY_MEDIUM) {
			this.mediumMessageBufferFreeBlocks.put(i);
		}
		for (int i = 0; i < largeMessageBuffer.length; i += CAPACITY_LARGE) {
			this.largeMessageBufferFreeBlocks.put(i);
		}
	}

	public Message getMessage() {
		int nextFreeSmallBlock = this.smallMessageBufferFreeBlocks.take();

		if (nextFreeSmallBlock == -1)
			return null;

		Message message = new Message(this);
		
		message.sharedBuffer = this.smallMessageBuffer;
		message.capacity = CAPACITY_SMALL;
		message.offset = nextFreeSmallBlock;
		message.length = 0;

		return message;
	}

	public boolean expandMessage(Message message) {
		if (message.capacity == CAPACITY_SMALL) {
			return moveMessage(message, this.smallMessageBufferFreeBlocks, this.mediumMessageBufferFreeBlocks, this.mediumMessageBuffer, CAPACITY_MEDIUM);
		} else if (message.capacity == CAPACITY_MEDIUM) {
			return moveMessage(message, this.mediumMessageBufferFreeBlocks, this.largeMessageBufferFreeBlocks, this.largeMessageBuffer, CAPACITY_LARGE);
		} else {
			return false;
		}
	}

	private boolean moveMessage(Message message, QueueIntFlip sourceQueue, QueueIntFlip targetQueue, byte[] destination, int newCapacity) {
		int nextFreeBlock = targetQueue.take();
		if (nextFreeBlock == -1)
			return false;

		System.arraycopy(message.sharedBuffer, message.offset, destination, nextFreeBlock, message.length);

		// Liberamos los bloques luego de la copia
		sourceQueue.put(message.offset);

		message.sharedBuffer = destination;
		message.offset = nextFreeBlock;
		message.capacity = newCapacity;
		return true;
	}
}