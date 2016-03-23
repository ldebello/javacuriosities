package ar.com.javacuriosities.networking.tcp.rmi;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/*
 * RMI (Remote Method Invocation) es la forma que nos ofrece Java para conectar dos
 * aplicaciones Java que están en distinto procesos incluso distintos ordenadores.
 * 
 * Cuando hablamos de IPC (Inter-Process Communication) es muy común escuchar RPC (Remote Procedure Call),
 * el cual es una forma para comunicar procesos por medio de una red, RMI seria similar pero basado para Java
 * y además de invocar un procedimiento podemos obtener objetos o sea es mas object oriented.
 * 
 * RMI se base en TCP/IP pero oculta la capa de comunicación al usuario
 * RMI utiliza Serialization para la comunicación entre los procesos
 * 
 * El puerto por default de RMI es 1099, del lado servidor tenemos que levantar el registry,
 * esto se puede hacer por línea de comandos o desde la app, si lo hacemos por línea de comandos
 * es importante que antes de iniciar el registry el classpath este bien configurado para poder exponer
 * nuestros objetos.
 * 
 * 
 * export CLASSPATH="<Server Classpath>"
 * rmiregistry &
 */
public class Server {

	public static void main(String args[]) {
		try {
			// En nuestro ejemplo, creamos el registry desde la app para que ya tome nuestro classpath
			LocateRegistry.createRegistry(1099);
			
			// Creamos el servicio a exportar
			HelloService serviceImpl = new HelloServiceImpl();
			
			/* 
			 * Usamos UnicastRemoteObject para exportar el objeto que deseamos, esto se encargara de crear
			 * las clases stub y skeleton que se encargan de la comunicación, hasta antes de Java 1.5 era necesario
			 * ejecutar rmic sobre la implementación del servicio para generar estas clases.
			 * 
			 * Stub: Es la representacion del lado cliente
			 * Skeleton: Es la representacion del lado server
			 */
			HelloService skeleton = (HelloService) UnicastRemoteObject.exportObject(serviceImpl, 0);

			// Obtenemos el registry 
			Registry registry = LocateRegistry.getRegistry();
			
			// Hacemos el binding entre un nombre y el objeto del lado server
			registry.bind(HelloService.class.getName(), skeleton);

			System.out.println("Server is up and running");
		} catch (RemoteException | AlreadyBoundException e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}
}