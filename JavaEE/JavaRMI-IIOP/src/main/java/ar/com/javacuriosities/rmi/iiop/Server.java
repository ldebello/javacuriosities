package ar.com.javacuriosities.rmi.iiop;

import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * RMI-IIOP (Remote Method Invocation - Internet Inter-ORB Protocol) Nos permite seguir programando como si usaramos RMI
 * pero correr sobre le ORB para agregar soporte a CORBA.
 *
 * A diferencia de RMI en lugar de registrar nuestra implementación en el RMI Register aquí lo haremos por JNDI. En este
 * ejemplo usamos una librería que nos permite tener un JNDI en memoria básico, dado que es en memoria.
 */
public class Server {
    public static void main(String[] args) {
        try {
            Context initialContext = new InitialContext();

            // Creamos el servant
            HelloServiceImpl helloRef = new HelloServiceImpl();

            // Publicamos la referencia en el Naming Service usando el API de JNDI (Java Naming and Directory Interface)
            initialContext.bind("HelloService", helloRef);

            System.out.println("Server is up and running");

            new Thread(new Client()).start();
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        }
    }
}
