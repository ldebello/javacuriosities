package ar.com.javacuriosities.nio.watcher;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

/*
 * A partir de Java 7 podemos utilizar los WatchService los cuales
 * nos permiten ser notificados frente a ciertas acciones que nosotros definimos
 * 
 * StandardWatchEventKinds:
 * 	- ENTRY_CREATE: Creación de un archivo o directorio
 * 	- ENTRY_DELETE: Borrado de un archivo o directorio
 * 	- ENTRY_MODIFY: Modificación de un archivo o directorio
 * 	- OVERFLOW: Indica que un evento fue perdido o descartado, no es necesario registrarse para este tipo de eventos.
 */
public class DirectoryWatcher {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		try {
			WatchService watcher = FileSystems.getDefault().newWatchService();

			Path directory = Paths.get(".");
			directory.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);

			System.out.println("Watch Service registered for directory: " + directory.getFileName());

			// Dejamos el servicio corriendo
			while (true) {

				// La WatchKey representa la registración
				WatchKey key;
				try {
					key = watcher.take();
				} catch (InterruptedException ex) {
					return;
				}

				// Solicitamos la lista de eventos para esta key
				for (WatchEvent<?> event : key.pollEvents()) {
					WatchEvent.Kind<?> kind = event.kind();

					WatchEvent<Path> watchEvent = (WatchEvent<Path>) event;
					Path fileName = watchEvent.context();

					System.out.println(kind.name() + ": " + fileName);
				}

				// Hacemos reset de la key para que siga siendo valida para los
				// eventos siguientes
				boolean valid = key.reset();
				if (!valid) {
					break;
				}
			}
		} catch (IOException e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}
}
