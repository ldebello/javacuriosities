package ar.com.javacuriosities.nio.zero_copy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;
import java.util.Arrays;

/*
 * Zero copy es una técnica que nos permite evitar la copia
 * redundante de datos entre buffers intermedios y asi reducir
 * el numero de context switch en la aplicación entre el user-space
 * y kernel-space.
 * 
 * Enviar datos de un archivo a un socket
 * 
 * Version sin Zero Copy:
 * 
 * 1- Al ejecutar un read() se genera un context switch del User mode al Kernel mode, internamente se ejecuta
 * un sys_read(), lo que copia la información del disco a un Read buffer del Kernel.
 * 2- Se ejecuta otro context switch y ahora se copian los datos del buffer desde el Kernel al User space y ahí termina el llamado
 * al read().
 * 3- Ahora al ejecutar un send() para el Socket se genera otro context switch al Kernel mode y una tercera copia de los
 * datos es realizada para dejarlos en el buffer que utiliza el Socket
 * 4- Ahora el llamado a send() retorna y en paralelo y de forma asincronica una cuarta copia es realizada desde el Socket buffer al NIC buffer por medio de DMA
 * 
 * Se ejecutan 4 context switch, 2 copias por DMA y 2 copias por el CPU
 * 
 * Usando Zero copy:
 * 
 * 1- Al ejecutar un transferTo() se genera un context switch del User mode al Kernel mode, y se copia la información
 * a un Read buffer del Kernel por medio de DMA
 * 2- Ahora se copia la información del Read buffer directamente al Socket Buffer (Esta copia la hace el CPU) y luego una tercera copia al NIC buffer usando DMA
 * 
 * Se ejecutan 2 context switch, 2 copias por DMA y 1 copia por el CPU
 * 
 * Usando Zero copy (Hardware con soporte para Scatter/Gather operations):
 * 
 * 1-Al ejecutar un transferTo() se genera un context switch del User mode al Kernel mode, y se copia la información
 * a un buffer del Kernel por medio de DMA.
 * 2- Ahora en lugar de copiar la información del buffer del Kernel a otro lado, se agrega la información con la ubicación y longitud de los datos, el DMA engine
 * se encarga de pasar esta información al engine del protocolo
 * 
 * Se ejecuta 1 context switch y 2 copias por DMA
 * 
 * Uso:
 * 
 * Para utilizar zero copy desde Java podemos utilizar los métodos transferTo y transferFrom
 * 
 * Limitaciones:
 * 
 * 	- Solo es aplicable a contenido estático
 * 	- No podemos aplicar si los datos tienen que ser modificados o con algún agregado adicional
 * 	- FileChannel solo puede ser usado para transferir datos de File -> File o File -> Socket
 * 
 */
public class ZeroCopy {
	
	private static final int BUFFER_SIZE = 8 * 1024;

	public static void main(String[] args) {
		try {
			// Creamos un archivo grande para la prueba, hay que incrementar el tamaño para ir viendo la diferencia
			File bigFile = createBigFile(100);
			
			File outputNormalCopy = new File("output_normal_copy.txt");
			File outputZeroCopy = new File("output_zero_copy.txt");
			
			outputNormalCopy.deleteOnExit();
			outputZeroCopy.deleteOnExit();
			
			copy(bigFile, outputNormalCopy);
			zeroCopy(bigFile, outputZeroCopy);
		} catch (IOException e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}
	
	public static File createBigFile(int sizeInMb) throws IOException {
		File file = File.createTempFile("data", ".txt");
	    file.deleteOnExit();
	    
	    char[] chars = new char[1024];
	    Arrays.fill(chars, 'A');
	    String longLine = new String(chars);
	    
	    long startTime = System.nanoTime();
	    PrintWriter printWriter = new PrintWriter(new FileWriter(file));
	    
	    for (int i = 0; i < sizeInMb * 1024; i++) {
	    	printWriter.println(longLine);
	    }
	        
	    printWriter.close();
	    long totalTime = System.nanoTime() - startTime;
	    
	    System.out.printf("Took %.3f seconds to write to a %d MB, file rate: %.1f MB/s%n", totalTime / 1e9, file.length() >> 20, file.length() * 1000.0 / totalTime);
	    
	    return file;
	}

	public static void copy(File from, File to) throws IOException {
		long startTime = System.nanoTime();
		byte[] data = new byte[BUFFER_SIZE];
		
		long bytesCopied = 0;
		long bytesToCopy = from.length();

		try (FileInputStream fis = new FileInputStream(from); FileOutputStream fos = new FileOutputStream(to)) {
			while (bytesCopied < bytesToCopy) {
				fis.read(data);
				fos.write(data);
				bytesCopied += data.length;
			}
			fos.flush();
		}
		
		long totalTime = System.nanoTime() - startTime;
		System.out.printf("Copy - Took %.3f seconds%n", totalTime / 1e9);
	}

	public static void zeroCopy(File from, File to) throws IOException {
		long startTime = System.nanoTime();
		
		try (FileInputStream fis = new FileInputStream(from); FileOutputStream fos = new FileOutputStream(to)) {
			FileChannel sourceChannel = fis.getChannel();
			FileChannel destinationChannel = fos.getChannel();
			sourceChannel.transferTo(0, sourceChannel.size(), destinationChannel);
		}
		
		long totalTime = System.nanoTime() - startTime;
		System.out.printf("Zero Copy - Took %.3f seconds%n", totalTime / 1e9);
	}
}