package ar.com.javacuriosities.networking.tcp.ssl;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLServerSocketFactory;

/*
 * Para realizar comunicaciones seguras en una red lo mas habitual es usar
 * Secure Sockets Layer (SSL)
 * - Esto es un protocolo sobre TCP
 * - Proporciona cifrado y autenticación
 * 
 * Este protocolo es usado en Hipertext Transfer Protocol Secure (HTTPS)
 * - Es una version de HTTP que se basa en SSL
 * - Proporciona aspectos de seguridad sobre HTTP
 * - Es usado para identificación y confidencialidad de la información que envían los usuarios
 * 
 * Podremos encontrar que existe SSL 3.0 y luego el Internet Engineering Task Force (IETF) lo actualizo ligeramente y lo llamo TLS 1.0
 * 
 * Java soporta SSL 3.0 y TLS 1.0
 * 
 * SSL permite usar distintos algoritmos de cifrados
 * 
 * Explicación:
 * 	En el mundo SSL los servidores deben autenticarse usando certificados (Para confirmar ser quien dicen ser), habitualmente los clientes no tienen que autenticarse
 * 	- Certificados:
 * 		Un servidor debe enviar su certificado al cliente
 * 		El cliente debe verificar el certificado del servidor con una CA (Certificate Authority)
 * 		Opcionalmente el cliente tambien se autenticara con un certificado al servidor
 * 
 * En el contexto de SSL, se utilizan dos tipos de archivos
 * - Truststore
 * 	Es un keystore que tiene el cliente y contiene los certificado de aquellas personas u organizaciones en las que confía el usuario
 * 
 * - Keystore
 * 	Es un keystore que tiene el servidor (Y también el cliente si tiene que autenticarse y contiene tanto su certificado como su clave privada)
 * 
 * Validación de los certificados de los servidores:
 * 	Por defecto se usa el truststore
 * 		$JRE_HOME/lib/security/cacerts
 * 	Podemos configurar uno con la propiedad
 * 		javax.net.ssl.trustStore
 * 
 * Validación de los certificados del cliente
 * 	Debemos establecer la ruta del fichero de certificados con la propiedad
 * 		javax.net.ssl.keyStore
 * 	Además debemos establecer la contraseña de acceso con la propiedad
 * 		javax.net.ssl.keyStorePassword
 * 
 * Generación del certificado (Otra opción es comprar un certificado en una CA)
 * 
 * keytool -keystore MyServerKeyStore -genkey -keypass GenericPassword -keyalg RSA -alias MyServerCert
 * 
 * Luego si pensamos usar este keyStore podemos copiarlo a la raíz del proyecto y ejecutar nuestro server con los siguientes parámetros
 * -Djavax.net.ssl.keyStore=MyServerKeyStore
 * -Djavax.net.ssl.keyStorePassword=GenericPassword
 * 
 * Ahora debemos exportar el certificado para el cliente
 * 
 * keytool -export -alias MyServerCert -keystore MyServerKeyStore -storepass GenericPassword -file Server.cer
 * 
 * Luego importamos el certificado en el trustStore
 * 
 * keytool -import -keystore MyClientTrustStore -storepass 123456 -file Server.cer
 * 
 * Luego debemos indicarle al cliente que trustStore usar
 * 
 * -Djavax.net.ssl.trustStore=MyClientTrustStore
 * -Djavax.net.ssl.trustStorePassword=123456
 * 
 * Para definir los socket servidor y cliente debemos usar factories ya definidas
 * Servidor
 * 	javax.net.ssl.SSLServerSocketFactory
 * 
 * Cliente
 * 	javax.net.ssl.SSLSocketFactory
 * 
 */
public class Step1SSLServer {

	public static volatile boolean isRunning = true;

	public static void main(String[] args) {
		try {
			// Aquí agregamos la properties desde el código lo ideal es pasarla como parámetros o leerlas de una locación externa
			System.setProperty("javax.net.ssl.keyStore", "MyServerKeyStore");
			/*
			 * Si definimos esta property la "keypass" tiene que coincidir con la "keyStorePassword"
			 * El archivo debe estar dentro del proyecto
			 */
			System.setProperty("javax.net.ssl.keyStorePassword", "GenericPassword");
	        // Podemos activar esta property en caso de solicitar información para el debugging
			// System.setProperty("javax.net.debug", "ssl");
			
			// KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
			// InputStream keyStoreStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("MyServerKeyStore");
			// keyStore.load(keyStoreStream, "123456".toCharArray());

			// Utilizamos un SocketFactory de SSL el cual maneja la creacion por nosotros
			ServerSocketFactory serverSocketFactory = SSLServerSocketFactory.getDefault();
			
			try (ServerSocket server = serverSocketFactory.createServerSocket(4000, 50, InetAddress.getLocalHost())) {

				/*
				 * El método accept() es bloqueante por lo cual genera un
				 * bloqueo hasta que llega una conexión
				 */
				while (isRunning) {
					try (Socket socket = server.accept()) {
						// Pedimos el output stream para enviar mensajes al cliente
						OutputStream os = socket.getOutputStream();

						// Usamos un wrapper el cual nos permite escribir valores
						// primitivos de forma simple
						DataOutputStream dos = new DataOutputStream(os);

						// Write message
						dos.writeUTF("Hi!!!");

						// Close output stream
						dos.close();
					}
				}
			}
		} catch (Exception e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}
}