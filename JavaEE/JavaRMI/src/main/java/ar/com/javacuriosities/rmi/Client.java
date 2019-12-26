package ar.com.javacuriosities.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

	public static void main(String[] args) {
		try {
			// Pedimos el registry del nodo remoto (En este caso localhost porque estamos en la misma PC, pero puede ser otra PC distinta)
			Registry registry = LocateRegistry.getRegistry("localhost");
			
			// El cliente debe conocer las interfaces del servicio (Usualmente se exporta un jar con las clases publicas)
			HelloService stub = (HelloService) registry.lookup(HelloService.class.getName());
			
			// Invocamos el método del objeto remoto
			String message = stub.sayHello();
			
			System.out.println(message);
		} catch (Exception e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}
}