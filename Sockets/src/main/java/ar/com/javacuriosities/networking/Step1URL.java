package ar.com.javacuriosities.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/*
 * URL (Uniform Resource Locator), esto es una referencia a nuestros recursos.
 * Esta formada por el schema/protocolo.
 * Los objetos URL luego de ser creados no pueden cambiar sus valores (protocol, host name, file, port)
 * 
 * Formato de una URL
 * 
 * <protocol>://<username>:<password>@<host>:<port>/<path>;<parameters>?<query>#<fragment>
 * 
 * Protocol
 * 	El protocolo de acceso
 * 
 * Host Name
 * 	Nombre de la machina donde esta el recurso
 *
 * Port Number
 * 	Numero de puerto a conectarse (Opcional dependiendo el protocolo)
 * 
 * Path
 * 	La ubicación del recurso
 * 
 * Fragment
 * 	Una referencia hacia un nombre dentro del recurso
 * 
 * La definición proviene de la RFC 2396
 * 
 * Hay una distinción entre URL y URI. Los URI definen recursos sintácticos de Internet. 
 * Esos recursos no tienen necesidad de poseer datos para localizar, esa es su diferencia. 
 * De hecho una URL es una caso de URI en el que los datos son localizables (es decir una URL hace referencia 
 * a datos que existen, una URI es teórica, los datos podrían no existir).
 */
public class Step1URL {
	public static void main(String[] args) {
        try {
        	// Creamos una URL desde un String que será parseado
            URL url1 = new URL("http://www.yahoo.com/resource/index.html?parameter1=value1&parameter1=value1#reference");

            // Creamos una URL indicando cada una de sus partes
            URL url2 = new URL("http", "www.yahoo.com", 80, "index.html");

            System.out.println("Informacion URL 1");
            System.out.println("Protocol: " + url1.getProtocol());
            System.out.println("Authority: " + url1.getAuthority());
            System.out.println("Host: " + url1.getHost());
            System.out.println("Port: " + url1.getPort());
            System.out.println("Path: " + url1.getPath());
            System.out.println("Query: " + url1.getQuery());
            System.out.println("File: " + url1.getFile());

            System.out.println("Informacion URL 2");
            System.out.println("Protocol: " + url2.getProtocol());
            System.out.println("Host: " + url2.getHost());
            System.out.println("Port: " + url2.getPort());
            System.out.println("Path: " + url2.getPath());
            System.out.println("File: " + url2.getFile());

            /*
             * Por medio de una URL podemos solicitar el flujo de información
             * Dado que es un flujo de entrada usaremos nos referimos a un InputStream
             */
            URL googleURL = new URL("http://www.google.com.ar");

            /*
             * Usamos un InputStreamReader para la conversion
             * entre un flujo de bytes a caracteres luego usamos
             * un BufferedReader para aplicar una capa de buffering sobre
             * el stream.
             * 
             * Usamos el feature introducido en Java 7 try-with-resources
             */
            try(BufferedReader  bufferedReader = new BufferedReader(new InputStreamReader(googleURL.openStream()))) {
            	String inputLine;
                while ((inputLine = bufferedReader.readLine()) != null) {
                    System.out.println(inputLine);
                }
            }

            /*
             * Otra opción para leer todo el input es usar la clase Scanner mas Pattern
             * 
             */
			// try(Scanner scanner = new Scanner(googleURL.openStream())) {
			// String data = scanner.useDelimiter("\\A").next();
			// System.out.println(data);
			// }
        } catch (MalformedURLException e) {
        	// Esta exception se arroja cuando se usa un protocolo no conocido o la URL no puede ser parseada
			// Log and Handle exception
			e.printStackTrace();
        } catch (IOException e) {
			// Log and Handle exception
			e.printStackTrace();
        }
    }
}