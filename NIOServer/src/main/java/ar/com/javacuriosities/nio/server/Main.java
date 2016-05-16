package ar.com.javacuriosities.nio.server;

import java.io.IOException;

import ar.com.javacuriosities.nio.server.http.reader.HttpMessageReaderFactory;
import ar.com.javacuriosities.nio.server.message.Message;
import ar.com.javacuriosities.nio.server.message.processor.IMessageProcessor;
import ar.com.javacuriosities.nio.server.message.writer.WriteProxy;

/*
 * Esta es la implementación básica de un Server implementado con NIO (Basado en https://github.com/jjenkov/java-nio-server).
 * La idea solo es entender los conceptos dados que ya hay frameworks que implementan esto
 * 	- Netty
 * 	- Grizzly
 * 
 */
public class Main {

	public static void main(String[] args) throws IOException {
		NIOServer nioServer = new NIOServer(8080, new HttpMessageReaderFactory(), new HttpMessageProcessor());
		nioServer.start();
	}

	private static final class HttpMessageProcessor implements IMessageProcessor {

		private static final String HTTP_RESPONSE = "HTTP/1.1 200 OK\r\n" + "Content-Length: 38\r\n" + "Content-Type: text/html\r\n"
				+ "\r\n" + "<html><body>Hello World!</body></html>";
		
		@Override
		public void process(Message message, WriteProxy writeProxy) {
			try {
				System.out.println("Message Received from socket: " + message.socketId);
				
				byte[] httpResponseBytes = HTTP_RESPONSE.getBytes("UTF-8");

				Message responseMessage = writeProxy.getMessage();
				responseMessage.socketId = message.socketId;
				responseMessage.writeToMessage(httpResponseBytes);

				writeProxy.enqueue(responseMessage);
			} catch (Exception e) {
				// Log and Handle exception
				e.printStackTrace();
			}
		}
	}
}