package ar.com.javacuriosities.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.GroupPrincipal;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Set;

/*
 * Una falencia del API del paquete java.io, era que no había forma de manejar metadata de directorios y archivos
 * 
 * 	- File Owners
 * 	- Permissions
 * 	- Time stamps
 * 	- DOS attributes
 * 	- Extended attributes
 * 
 * La forma de manejarlos es por medio de vistas donde engloban estos atributos
 */
public class Lesson06FileAttributes {
	public static void main(String[] args) {
		try {
			Path path = Paths.get("metadata.txt");

			Files.deleteIfExists(path);
			
			Files.createFile(path);
			
			BasicFileAttributeView basicView = Files.getFileAttributeView(path, BasicFileAttributeView.class);
			
			BasicFileAttributes basicAttributes = basicView.readAttributes();
			boolean isDirectory = basicAttributes.isDirectory();
			FileTime lastModifiedTime = basicAttributes.lastModifiedTime();
			
			System.out.println(isDirectory);
			System.out.println(lastModifiedTime);
			
			PosixFileAttributeView posixView = Files.getFileAttributeView(path, PosixFileAttributeView.class);
			PosixFileAttributes posixAttributes = posixView.readAttributes();
			
			GroupPrincipal group = posixAttributes.group();
			
			Set<PosixFilePermission> permissions = posixAttributes.permissions();
			permissions.add(PosixFilePermission.OWNER_EXECUTE);
			posixView.setPermissions(permissions);
			
			System.out.println(group);
			
		} catch (IOException e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}
}
