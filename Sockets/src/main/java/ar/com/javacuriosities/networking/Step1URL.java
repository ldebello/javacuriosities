package ar.com.javacuriosities.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/*
 * URL (Uniform Resource Locator)
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
 * La definición proviene de la RFC 2396.
 *
 * Una URL es un caso especifico de URI, en el cual los datos son localizables (es decir una URL hace referencia
 * a datos que existen, una URI es teórica, los datos podrían no existir).
 */
public class Step1URL {
	public static void main(String[] args) {
        try {
        	// Creamos una URL desde un String que será parseado
            URL url1 = new URL("http://www.google.com/resource/index.html?parameter1=value1&parameter1=value1#reference");

            // Creamos una URL indicando cada una de sus partes
            URL url2 = new URL("http", "www.google.com", 80, "index.html");

            System.out.println("URL 1");
            System.out.println("Protocol: " + url1.getProtocol());
            System.out.println("Authority: " + url1.getAuthority());
            System.out.println("Host: " + url1.getHost());
            System.out.println("Port: " + url1.getPort());
            System.out.println("Path: " + url1.getPath());
            System.out.println("Query: " + url1.getQuery());
            System.out.println("File: " + url1.getFile());

            System.out.println("URL 2");
            System.out.println("Protocol: " + url2.getProtocol());
            System.out.println("Host: " + url2.getHost());
            System.out.println("Port: " + url2.getPort());
            System.out.println("Path: " + url2.getPath());
            System.out.println("File: " + url2.getFile());

            /*
             * Por medio de una URL podemos establecer una conexión y consumir su flujo de información
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
        } catch (MalformedURLException e) {
			// Log and Handle exception
			e.printStackTrace();
        } catch (IOException e) {
			// Log and Handle exception
			e.printStackTrace();
        }
    }
}