package ar.com.javacuriosities.rmi;

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