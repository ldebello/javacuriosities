package ar.com.javacuriosities.nio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
 * La interfaz Path fue agregada como parte de NIO 2, esta representa un Path en el file system, esto
 * puede ser un archivo o un directorio, los path pueden ser relativos como absolutos.
 * 
 * Path es similar a la clase File del paquete java.io
 * 
 * Las ventajas de esta nueva implementación es que nos deja interactuar con cada componente del path
 * de forma simple y programática, asi como crear subpath, comparar el ultimo componente, resolver otros path
 * en base a uno existente, etc.
 */
public class Lesson02Path {
	public static void main(String[] args) {
		try {
			// Creamos un path a un archivo que no existe
			Path path = Paths.get("../javacuriosities/nio/./data.txt");
			
			// Podemos ir y volver desde un Path a un File y viceversa
			File fileFromPath = path.toFile();
			Path pathFromFile = fileFromPath.toPath();

			System.out.println(fileFromPath);
			System.out.println(pathFromFile);

			System.out.println(fileFromPath.getAbsolutePath()); // Resuelve el absolute path sin remover las expresiones "." ni ".."
			System.out.println(fileFromPath.getCanonicalPath()); // Resuelve todo el path
			
			System.out.println(pathFromFile.toAbsolutePath());
			System.out.println(pathFromFile.normalize().toAbsolutePath()); // El método normalize remueve las expresiones "." y ".." del path siempre y cuando no estén al comienzo
		} catch (IOException e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}
}
