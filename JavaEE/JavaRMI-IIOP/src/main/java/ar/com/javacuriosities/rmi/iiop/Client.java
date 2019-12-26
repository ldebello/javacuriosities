package ar.com.javacuriosities.rmi.iiop;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

public class Client implements Runnable {

    @Override
    public void run() {
        try {
            Context initialContext = new InitialContext();

            // Obtenemos una referencia por medio de JNDI
            Object objRef = initialContext.lookup("HelloService");

            System.out.println("Referencia: " + objRef);

            // Hacemos narrowing de la referencia al tipo concreto para invocar al método correspondiente
            HelloService remoteService = (HelloService) PortableRemoteObject.narrow(objRef, HelloService.class);

            String message = remoteService.sayHello();

            System.out.println(message);
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        }
    }
}
