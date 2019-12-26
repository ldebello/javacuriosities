package ar.com.javacuriosities.rmi.iiop;

import javax.rmi.PortableRemoteObject;
import java.rmi.RemoteException;

public class HelloServiceImpl extends PortableRemoteObject implements HelloService {
    public HelloServiceImpl() throws RemoteException {
        super(); // Se ejecuta el RMI linking y la inicialización para el remote object
    }

    @Override
    public String sayHello() throws RemoteException {
        return "Hello World!!!";
    }
}
