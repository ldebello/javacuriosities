package ar.com.javacuriosities.nio.server;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import ar.com.javacuriosities.nio.server.clients.ClientSocket;
import ar.com.javacuriosities.nio.server.message.MessageBuffer;
import ar.com.javacuriosities.nio.server.message.processor.IMessageProcessor;
import ar.com.javacuriosities.nio.server.message.reader.IMessageReaderFactory;
import ar.com.javacuriosities.nio.server.tasks.AccepterTask;
import ar.com.javacuriosities.nio.server.tasks.ProcessorTask;

/*
 * El server solo utiliza 2 threads uno para aceptar conexiones y otro para ir procesando los request
 */
public class NIOServer {

	private int port = 0;
	
	// Runnables que maneja el server
    private AccepterTask  accepterTask  = null;
    private ProcessorTask processorTask = null;

    private IMessageReaderFactory messageReaderFactory = null;
    private IMessageProcessor     messageProcessor = null;

    public NIOServer(int port, IMessageReaderFactory messageReaderFactory, IMessageProcessor messageProcessor) {
        this.port = port;
        this.messageReaderFactory = messageReaderFactory;
        this.messageProcessor = messageProcessor;
    }

    public void start() throws IOException {
    	MessageBuffer readBuffer  = new MessageBuffer();
        MessageBuffer writeBuffer = new MessageBuffer();
        
    	Queue<ClientSocket> clientSocketQueue = new ArrayBlockingQueue<ClientSocket>(1024);

        accepterTask  = new AccepterTask(clientSocketQueue, port);
        processorTask = new ProcessorTask(clientSocketQueue, readBuffer, writeBuffer, messageReaderFactory, messageProcessor);

        Thread accepterThread  = new Thread(this.accepterTask);
        Thread processorThread = new Thread(this.processorTask);

        accepterThread.start();
        processorThread.start();
    }
}