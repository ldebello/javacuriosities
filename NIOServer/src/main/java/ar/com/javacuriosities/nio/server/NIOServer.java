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
        
    	Queue<ClientSocket> clientsQueue = new ArrayBlockingQueue<ClientSocket>(1024);

        Thread accepter  = new Thread(new AccepterTask(clientsQueue, port));
        Thread processor = new Thread(new ProcessorTask(clientsQueue, readBuffer, writeBuffer, messageReaderFactory, messageProcessor));

        accepter.start();
        processor.start();
    }
}