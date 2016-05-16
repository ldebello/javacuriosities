package ar.com.javacuriosities.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/*
 * Pipe es un channel el cual permite leer y escribir datos en secuencia. Pipe asegura
 * que los datos estarán en el mismo orden en que son escritos, pueden ser utilizados para enviar
 * datos entre Threads
 */
public class Lesson07Pipe {
	public static void main(String[] args) {

		try {
			// Creamos una instancia de Pipe
			Pipe pipe = Pipe.open();

			// Obtenemos el sink channel, el cual usamos para la escritura
			Pipe.SinkChannel sinkChannel = pipe.sink();

			String message = "Data sent through Java NIO Channels Pipe";

			ByteBuffer buffer = ByteBuffer.allocate(512);
			buffer.clear();
			buffer.put(message.getBytes());

			buffer.flip();
			
			// Escribimos el mensaje en el channel
			while (buffer.hasRemaining()) {
				sinkChannel.write(buffer);
			}
			
			// Obtenemos el source channel el cual usamos para leer
			Pipe.SourceChannel sourceChannel = pipe.source();
			buffer = ByteBuffer.allocate(512);

			// Vamos leyendo los datos y escribiendo en la consola
			while (sourceChannel.read(buffer) > 0) {
				buffer.flip();

				while (buffer.hasRemaining()) {
					System.out.print((char) buffer.get());
				}

				buffer.clear();
			}
		} catch (IOException e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}
}