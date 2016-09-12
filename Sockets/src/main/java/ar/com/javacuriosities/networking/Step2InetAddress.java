package ar.com.javacuriosities.networking;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/*
 * La clase InetAddress representa una dirección IP, ofrece métodos 
 * para obtener direcciones IP por medio de nombres o IPs, para la resolución
 * de nombres a IP utiliza configuración del sistema y servicios DNS
 * 
 * La resolución de Host Name puede estar en cache (TTL: Time-to-live) ya sea por resultado positivo o negativo, esto se 
 * lo conoce como
 * 
 * Resolución positiva:
 * networkaddress.cache.ttl (default: -1)
 * -1 --> Cache forever
 * 
 * Resolución negativa
 * networkaddress.cache.negative.ttl (default: 10)
 * 0 --> Never cache
 * -1 --> Cache forever
 * 
 * Dependiendo si es IPv6 el objeto retornado es Inet6Address y si es IPv4 retorna Inet4Address
 *  
 */
public class Step2InetAddress {

	public static void main(String[] args) {
		try {
			InetAddress localHost = InetAddress.getLocalHost();
			printInformation(localHost);

			InetAddress loopback = InetAddress.getLoopbackAddress();
			printInformation(loopback);

			InetAddress loopbackByIp = InetAddress.getByAddress(new byte[] {
					127, 0, 0, 1 });
			printInformation(loopbackByIp);

			InetAddress googleHost = InetAddress.getByName("www.google.com");
			printInformation(googleHost);

			System.out.println("****************");
			// Dado que un dominio puede tener multiples IP asignadas podemos usar el método getAllByName()
			InetAddress[] multiplesInetAddresses = InetAddress.getAllByName("www.google.com");

			for (InetAddress ipAddress : multiplesInetAddresses) {
				System.out.println(ipAddress);
			}

			// Obtenemos los bytes de la IP
			byte[] bytes = localHost.getAddress();

			/*
			 * Si solicitamos la IP esta es devuelta en un array de bytes el
			 * cual debemos tener cuidado al convertirlo.
			 * 
			 * El siguiente ciclo convierte los bytes de la dirección IP a
			 * valores sin signo y los presenta separados por espacios, recordar
			 * que en Java no hay tipo unsigned por eso pueden ser negativos y
			 * si es negativo significa que su valor es mas alto que 127
			 */
			for (int currentByte = 0; currentByte < bytes.length; currentByte++) {
				int fixingConvertion = bytes[currentByte] < 0 ? bytes[currentByte] + 256
						: bytes[currentByte];
				System.out.print(fixingConvertion + ".");
			}

			// InetAddress.getByName("www.no_existo.com.ar");
		} catch (UnknownHostException e) {
			// Esta exception es arrojada cuando una IP o Host no puede ser
			// determinado
			// Log and Handle exception
			e.printStackTrace();
		} catch (IOException e) {
			// Log and Handle exception
			e.printStackTrace();
		}

	}

	private static void printInformation(InetAddress inetAddress)
			throws IOException {
		System.out.println("****************");
		System.out.println("Information --> " + inetAddress);
		System.out.println("InetAddress: " + inetAddress);
		System.out.println("Host Name: " + inetAddress.getHostName());
		// Intentara devolver el FQDN
		System.out.println("Canonical Host Name: "
				+ inetAddress.getCanonicalHostName());
		System.out.println("Host Address: " + inetAddress.getHostAddress());
		System.out.println("Is Any Local: " + inetAddress.isAnyLocalAddress());
		System.out.println(" - Is Link Local: "
				+ inetAddress.isLinkLocalAddress());
		System.out
				.println(" - Is Loopback: " + inetAddress.isLoopbackAddress());
		System.out.println(" - Is Multicast: "
				+ inetAddress.isMulticastAddress());
		System.out.println(" - Is Site Local: "
				+ inetAddress.isSiteLocalAddress());
		System.out.println("Is Reachable in 2 seconds: "
				+ inetAddress.isReachable(2000));
	}
}