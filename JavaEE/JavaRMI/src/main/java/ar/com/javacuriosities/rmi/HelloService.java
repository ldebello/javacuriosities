package ar.com.javacuriosities.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/*
 * Esta es la interfaz que usamos para definir nuestro objeto remoto
 * Es importante ver que extendemos Remote
 */
public interface HelloService extends Remote {
    String sayHello() throws RemoteException;
}