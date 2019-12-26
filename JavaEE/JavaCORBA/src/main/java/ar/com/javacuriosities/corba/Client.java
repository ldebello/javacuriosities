package ar.com.javacuriosities.corba;

import HelloApp.HelloService;
import HelloApp.HelloServiceHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

public class Client {
    public static void main(String[] args) {
        try {
            // Creamos e inicializamos el ORB
            ORB orb = ORB.init(args, null);

            // Obtenemos una referencia al "NameService"
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");

            // Resolvemos la referencia
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // Resolvemos la referencia a "Hello"
            HelloService href = HelloServiceHelper.narrow(ncRef.resolve_str("Hello"));

            String hello = href.sayHello();
            System.out.println(hello);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
