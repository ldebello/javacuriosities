package ar.com.javacuriosities.networking.tcp.rmi;

import java.rmi.RemoteException;

/*
 * Aquí implementamos nuestro objeto remoto
 */
public class HelloServiceImpl implements HelloService {

	@Override
	public String sayHello() throws RemoteException {
		return "Hello World";
	}
}