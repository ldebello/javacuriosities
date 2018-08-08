package ar.com.javacuriosities.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/*
 * Java NIO (New IO) fue introducido a partir de Java 1.4, luego en Java 1.7 se extendió este modelo con 
 * lo que se conoce como NIO 2.
 * 
 * IO vs NIO:
 * 
 * IO				NIO
 * Stream Oriented	Buffer Oriented
 * Blocking IO		Non blocking IO
 * 					Selectors
 * 
 * Stream Oriented: Leemos los datos de a uno o mas bytes y estos no son cacheados, tampoco podemos retroceder en el flujo de datos
 * Blocking IO: Las operaciones bloquean al hilo actual hasta que ejecuta el system call de lectura
 * 
 * Buffer Oriented: Los datos son almacenados en un buffer y podemos movernos en el mismo
 * Non blocking IO: Permite realizar operaciones sobre el channel sin necesidad de esperar que esa operación termine, dependiendo el channel puede ser
 * que se pueda habilitar o no el soporte para esto
 * Selectors: Un selector puede monitorear multiples channels esperando determinados eventos
 * 
 * NIO:
 * 	- Channels
 * 	- Buffers
 * 	- Selectors
 * 
 * NIO 2:
 * 	- File System API
 * 	- Socket y Asynchronous I/O Channel
 * 	- SCTP (Stream Control Transport Protocol)
 * 	- SDP (Socket Direct Protocol)
 * 
 * Channels and Buffers:
 * Los datos son siempre leídos desde un channel al buffer o escritos del buffer al channel
 * 
 * Channels:
 * 	- FileChannel: No soporta el modo Non-blocking, dado que Unix no soporta non-blocking I/O para archivos, pero en Java 7 se agrego AsynchronousFileChannel, el cual provee soporte ya sea de forma nativa si lo soporta o con multiples threads
 * 	- DatagramChannel
 * 	- SocketChannel
 * 	- ServerSocketChannel
 * 
 * Buffers:
 * 	- ByteBuffer
 * 	- CharBuffer
 * 	- DoubleBuffer
 * 	- FloatBuffer
 * 	- IntBuffer
 * 	- LongBuffer
 * 	- ShortBuffer
 * 
 * Los Buffers cuentan con tres atributos de importancia y algunos métodos
 * 
 * 	- capacity: Capacidad total del buffer
 * 	- position: Posición actual en el buffer
 * 	- limit: Cantidad de datos escritos en el buffer
 * 
 * 	- flip(): Mueve la posición al inicio y deja el limit asignado a la posición actual para pasar al modo lectura
 * 	- rewind(): Mueve la posición al inicio
 * 	- clear(): Vuelve la posición a cero para sobreescribir el buffer
 * 	- compact(): Los datos no leídos se mueven al comienzo del buffer
 * 	- mark(): Se guarda la posición actual para luego poder hacer un reset
 * 	- reset(): Vuelve la posición a la ultima posición donde se hizo mark
 */
public class Lesson01Introduction {
	public static void main(String[] args) {
		try {
			readChannel();
		    writeChannel();
		    readIntoMultiplesBuffers();
		    writeChannelFromMultiplesBuffers();
		} catch (IOException e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}

	private static void readChannel() throws FileNotFoundException, IOException {
		// Usamos la clase File perteneciente al paquete IO, luego pasaremos a usar Path que pertenece a NIO
		File file = new File("message.txt");

		FileInputStream fis = new FileInputStream(file);
		
		FileChannel channel = fis.getChannel();
		
		// El buffer puede estar almacenado en el Heap o fuera del mismo si usamos el método "allocateDirect"
		ByteBuffer buffer = ByteBuffer.allocate(5);
		
		int bytesRead = channel.read(buffer);
		while (bytesRead != -1) {
		  System.out.println("Number of bytes read " + bytesRead);
		  
		  // El método flip modifica el estado interno para asignar los indices de forma correcta
		  buffer.flip();

		  System.out.println("Current data");
		  while(buffer.hasRemaining()){
		      System.out.print((char) buffer.get());
		  }

		  System.out.println();
		  
		  // Posiciona el índice al principio para sobreescribir los datos, otra opción es usar el método compact el cual mueve al principio los datos que no han sido leídos
		  buffer.clear();

		  bytesRead = channel.read(buffer);
		}
		
		// Si cerramos el IS también se cierra el channel asociado
		fis.close();
	}
	
	private static void writeChannel() throws FileNotFoundException, IOException {
		File file = new File("output.txt");
		if (file.exists()) {
			file.delete();
		}
		
		FileOutputStream fos = new FileOutputStream(file);
		FileChannel channel = fos.getChannel();
		
		String message = "Content written using FileChannel";
		
		channel.write(ByteBuffer.wrap(message.getBytes()));
		
		fos.close();
	}
	
	private static void readIntoMultiplesBuffers() throws FileNotFoundException, IOException {
		File file = new File("data.txt");

		FileInputStream fis = new FileInputStream(file);
		
		FileChannel channel = fis.getChannel();
		
		ByteBuffer header = ByteBuffer.allocate(6);
		ByteBuffer body   = ByteBuffer.allocate(4);

		ByteBuffer[] bufferArray = { header, body };

		// Este método recibe multiples buffers y los va llenando de acuerdo al orden en que los recibió
		channel.read(bufferArray);

		fis.close();
	}
	
	private static void writeChannelFromMultiplesBuffers() throws FileNotFoundException, IOException {
		File file = new File("output_multiples_buffers.txt");
		if (file.exists()) {
			file.delete();
		}
		
		FileOutputStream fos = new FileOutputStream(file);
		FileChannel channel = fos.getChannel();
		
		byte[] headerBytes = ("Header" + System.lineSeparator()).getBytes();
		byte[] bodyBytes = ("Body" + System.lineSeparator()).getBytes();
		
		ByteBuffer header = ByteBuffer.allocate(headerBytes.length);
		ByteBuffer body   = ByteBuffer.allocate(bodyBytes.length);

		header.put(headerBytes);
		body.put(bodyBytes);
		
		ByteBuffer[] bufferArray = { header, body };

		header.flip();
		body.flip();
		
		channel.write(bufferArray);
		
		fos.close();
	}
}