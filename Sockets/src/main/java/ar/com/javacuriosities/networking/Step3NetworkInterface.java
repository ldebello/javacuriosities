package ar.com.javacuriosities.networking;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;

/*
 * Cuando hablamos de Network Interface nos referimos al punto de conexión
 * entre la computadora y una red publica o privada.
 * Para poder lograr esto utilizaremos NIC (Network Interface Card) las cuales
 * pueden ser físicas o virtuales, el ejemplo mas conocido de esto es el loopback 
 * IPv4 --> 127.0.0.1
 * IPv6 --> ::1
 * 
 * La clase NetworkInterface nos permite acceder a las distintas NIC de nuestra
 * PC, además se permite una agrupación jerárquica por medio de los métodos getParent() y getSubInterfaces().
 * 
 * Las NIC tienen su propia dirección --> Media Access Control (MAC)
 * 
 */
public class Step3NetworkInterface {

	public static void main(String[] args) {
		try {
			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface
					.getNetworkInterfaces();

			for (NetworkInterface networkInterface : Collections
					.list(networkInterfaces)) {
				displayNetworkInterfaceInformation(networkInterface);
				displayInetAddresses(networkInterface);
				displaySubInterfaces(networkInterface);
			}
		} catch (SocketException e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}

	private static void displayNetworkInterfaceInformation(
			NetworkInterface networkInterface) throws SocketException {
		System.out
				.println("Display name: " + networkInterface.getDisplayName());
		System.out.println("Name: " + networkInterface.getName());

		System.out.println("Up: " + networkInterface.isUp());
		System.out.println("Loopback: " + networkInterface.isLoopback());
		System.out
				.println("PointToPoint: " + networkInterface.isPointToPoint());
		System.out.println("Supports multicast: "
				+ networkInterface.supportsMulticast());
		System.out.println("Virtual: " + networkInterface.isVirtual());
		System.out.println("Hardware address: "
				+ Arrays.toString(networkInterface.getHardwareAddress()));
		System.out.println("MTU: " + networkInterface.getMTU());
	}

	private static void displayInetAddresses(NetworkInterface networkInterface) {
		Enumeration<InetAddress> inetAddresses = networkInterface
				.getInetAddresses();

		for (InetAddress inetAddress : Collections.list(inetAddresses)) {
			System.out.println("\tInet Address: " + inetAddress);
		}
		System.out.println("*******************");
	}

	private static void displaySubInterfaces(NetworkInterface networkInterface)
			throws SocketException {
		Enumeration<NetworkInterface> subNetworkInterfaces = networkInterface
				.getSubInterfaces();

		for (NetworkInterface subIf : Collections.list(subNetworkInterfaces)) {
			System.out.println("\tSub Interface Display name: "
					+ subIf.getDisplayName());
			System.out.println("\tSub Interface Name: " + subIf.getName());
		}
	}
}