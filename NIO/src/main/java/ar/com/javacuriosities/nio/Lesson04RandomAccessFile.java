package ar.com.javacuriosities.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
 * Como parte de NIO 2 se mejoro el manejo de RAFs (Random Access Files),
 * lo cual nos permite ir a un ubicación especifica del archivo y leer o escribir
 * la cantidad de bytes que necesitemos
 */
public class Lesson04RandomAccessFile {
	public static void main(String[] args) {
		try {
			randomAccessFileUsingIO();
			randomAccessFileUsingNIO();
		} catch (IOException e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}

	private static void randomAccessFileUsingIO() throws IOException {
		RandomAccessFile raf = new RandomAccessFile("message.txt", "rw");
		raf.seek(6);
		int currentByte = raf.read();
		System.out.println((char) currentByte);
		raf.close();
	}
	
	private static void randomAccessFileUsingNIO() throws IOException {
		Path path = Paths.get("message.txt");
		try(SeekableByteChannel seekableByteChannel = Files.newByteChannel(path)) {
			ByteBuffer buffer = ByteBuffer.allocate(5);
			seekableByteChannel.position(6);
			seekableByteChannel.read(buffer);
			
			buffer.flip();
			System.out.println((char) buffer.get());
		}
	}
}
