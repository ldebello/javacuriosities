package ar.com.javacuriosities.nio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

/*
 * La clase Files utiliza instancias de Path para sus operaciones, y se agrego como 
 * mejora a muchas deficiencias existentes en la parte de I/O
 * 
 * Operaciones:
 * 	- Chequear existencia de archivos y directorios
 * 	- Crear archivos, directorios y links
 * 	- Mover/Copiar/Borrar archivos y directorios
 * 	- Recorrer la jerarquía de archivos
 * 	- Crear archivos, directorios temporales
 * 	- Chequear/Modificar atributos de los archivos y directorios
 * 
 * StandardCopyOption:
 * 	- REPLACE_EXISTING: Reemplaza el archivo si existe
 * 	- COPY_ATTRIBUTES: Además copia los atributos al nuevo archivo
 * 	- ATOMIC_MOVE: Mueve el archivo como una operación atomica del FS (File System)
 * 
 * FileVisitResult:
 * 	- CONTINUE: Continua con el recorrido
 * 	- TERMINATE: Termina el recorrido
 * 	- SKIP_SUBTREE : Continua el recorrido sin visitar las entradas del directorio
 * 	- SKIP_SIBLINGS: Continua el recorrido sin visitar los archivos o directorios continuos
 * 
 * StandardOpenOption:
 * 	- READ: Abre el archivo en modo lectura
 * 	- WRITE: Abre el archivo en modo escritura
 * 	- APPEND: Abre el archivo para agregar contenido al final
 * 	- TRUNCATE_EXISTING: Trunca el tamaño del archivo a 0 y escribe el contenido
 * 	- CREATE: Crea el archivo
 * 	- CREATE_NEW: Crea el archivo pero si existe falla
 * 	- DELETE_ON_CLOSE: Siempre y cuando pueda borra el archivo al cerrarlo
 * 	- SPARSE: Indica que va a crear un sparse file, esta opción es ignorada si el sistema no soporta este tipo de archivos (Un Sparse File es un archivo que varios blanco el cual el SO puede manejar de forma optima sin ocupar el espacio innecesario)
 * 	- SYNC: Por cada actualización, sincroniza el contenido y la metadata para ser escritos de forma sincronica
 * 	- DSYNC: Esta opción es un subconjunto de SYNC y solo sincroniza el contenido
 */
public class Lesson03Files {
	public static void main(String[] args) {
		try {
			Path source = Paths.get("data.txt");
			Path target = Paths.get("data_copy.txt");

			// Nos permite ver si un path existe y definir si queremos seguir
			// los symbolic links
			boolean exists = Files.exists(source, LinkOption.NOFOLLOW_LINKS);

			System.out.println("Exists? " + exists);

			Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
			
			Files.delete(source);
			
			Files.move(target, source);
			
			Files.walkFileTree(Paths.get("."), new FileVisitor<Path>() {
				  @Override
				  public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				    System.out.println("Pre visit dir:" + dir);
				    return FileVisitResult.CONTINUE;
				  }

				  @Override
				  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				    System.out.println("Visit file: " + file);
				    return FileVisitResult.CONTINUE;
				  }

				  @Override
				  public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
				    System.out.println("Visit file failed: " + file);
				    return FileVisitResult.CONTINUE;
				  }

				  @Override
				  public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
				    System.out.println("Post visit directory: " + dir);
				    return FileVisitResult.CONTINUE;
				  }
				});
			
			// Java 8 incluye el método "readAllLines"
			List<String> lines = Files.readAllLines(source);
			System.out.println(lines);
			
			readFileUsingIO();
			readFileUsingNIO();
			
			writingFileUsingIO();
			writingFileUsingNIO();
		} catch (IOException e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}

	private static void readFileUsingIO() throws IOException {
		// Estas clases implementan el patron Decorator y agregan funcionalidad sobre lo que decoran
		File file = new File("data.txt");
		FileReader fr = new FileReader(file);
		try (BufferedReader bufferedReader = new BufferedReader(fr)) {
			String line = bufferedReader.readLine();
			while (line != null) {
				System.out.println(line);
				line = bufferedReader.readLine();
			}
		}
	}
	

	private static void writingFileUsingIO() throws IOException {
		String message = "IO Example";
		File file = new File("file_io.txt");
		FileWriter fw = new FileWriter(file);
		try (BufferedWriter bw = new BufferedWriter(fw)) {
			bw.write(message);
		}
	}
	
	private static void readFileUsingNIO() throws IOException {
		Path path = Paths.get("data.txt");
		try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
			String line = bufferedReader.readLine();
			while (line != null) {
				System.out.println(line);
				line = bufferedReader.readLine();
			}
		}
	}
	

	private static void writingFileUsingNIO() throws IOException {
		String message = "NIO Example";
		Path path = Paths.get("file_nio.txt");
		try (BufferedWriter bw = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
			bw.write(message);
		}
	}
}
