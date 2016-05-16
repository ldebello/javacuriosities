package ar.com.javacuriosities.nio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/*
 * Como parte de NIO 2 se agrego al API Asynchronous IO lo cual permite realizar estas operaciones para Sockets y Files
 * 
 * API:
 * 	- Future Style: La operación inicia un IO y retorna un Future
 * 	- Callback Style: Esperamos un evento por medio de un CompletionHandler
 * 
 */
public class Lesson05AsynchronousIO {
	public static void main(String[] args) {
		try {
			readFileAsynchronous();
			writeFileAsynchronous();
		} catch (Exception e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}

	private static void readFileAsynchronous() throws Exception {
		Path path = Paths.get("message.txt");

		AsynchronousFileChannel channel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);

		ByteBuffer buffer = ByteBuffer.allocate(1024);
		long position = 0;

		// Podemos usar un Future para ejecutar la operación
		Future<Integer> operation = channel.read(buffer, position);

		// Esperamos que la operación termine, esto no es eficiente pero es una
		// forma
		while (!operation.isDone())
			;

		buffer.flip();
		byte[] data = new byte[buffer.limit()];
		buffer.get(data);
		System.out.println(new String(data));
		buffer.clear();

		// Otra forma mas eficiente es usando un Callback
		channel.read(buffer, position, buffer, new CompletionHandler<Integer, ByteBuffer>() {
			@Override
			public void completed(Integer result, ByteBuffer attachment) {
				System.out.println("Result: " + result);

				attachment.flip();
				byte[] data = new byte[attachment.limit()];
				attachment.get(data);
				System.out.println(new String(data));
				attachment.clear();
			}

			@Override
			public void failed(Throwable exc, ByteBuffer attachment) {
			}
		});
		
		// Esperamos un segundo para ver el resultado del callback porque sino nuestro programa puede terminar antes
		TimeUnit.SECONDS.sleep(1);
	}
	
	private static void writeFileAsynchronous() throws Exception {
		Path path = Paths.get("asynchronous.txt");
		
		Files.deleteIfExists(path);
		
		Files.createFile(path);
		
		AsynchronousFileChannel channel =  AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);

		ByteBuffer buffer = ByteBuffer.allocate(1024);
		long position = 0;

		buffer.put("Asynchronous message".getBytes());
		buffer.flip();

		Future<Integer> operation = channel.write(buffer, position);
		buffer.clear();

		while(!operation.isDone());

		ByteBuffer bufferForHandler = ByteBuffer.allocate(1024);
		bufferForHandler.put("Asynchronous message".getBytes());
		bufferForHandler.flip();
		
		channel.write(bufferForHandler, position, null, new CompletionHandler<Integer, ByteBuffer>() {

		    @Override
		    public void completed(Integer result, ByteBuffer attachment) {
		        System.out.println("Bytes written: " + result);
		    }

		    @Override
		    public void failed(Throwable e, ByteBuffer attachment) {
		        System.out.println("Write failed");
		        e.printStackTrace();
		    }
		});
		
		// Esperamos un segundo para ver el resultado del callback porque sino nuestro programa puede terminar antes
		TimeUnit.SECONDS.sleep(1);
	}
}