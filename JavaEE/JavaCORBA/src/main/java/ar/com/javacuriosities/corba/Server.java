package ar.com.javacuriosities.corba;

import HelloApp.HelloService;
import HelloApp.HelloServiceHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

/**
 * Common Object Request Broker Architecture (CORBA) es un estándar definido por Object Management Group (OMG) que
 * permite que dos aplicaciones se comuniquen entre si por medio de un canal llamado Object Request Broker (ORB).
 *
 * Es agnóstico de Java y puede ser usado con multiples lenguajes, usando un archivo .idl (Interface Definition), y luego
 * generando las clases necesarias desde el idlj incluido en el JDK, este ejemplo usa un maven plugin para ejecutar eso.
 *
 * Algunas definiciones:
 *
 * Object: Es una entidad CORBA que consiste de una identidad, interfaz e implementation que es conocidad como Servant.
 * Servant: Es la implementación en un lenguaje de programación que soporta las operaciones definidas en el CORBA IDL Interface.
 * Estos pueden ser escritos en varios lenguajes C, C++, Java, Smalltalk y Ada.
 * Stub: Representa el proxy del lado cliente
 * Skeleton: Corresponde al implementación del lado server.
 * Portable Object Adapter (POA): Es un mecanismo para conectar un request que usa un object reference con el código para procesar ese request.
 *
 * Nota: Necesitamos el ORB ejecutándose, para eso podemos usar "Object Request Broker Daemon". Desde consola solo debemos
 * invocar orbd
 */
public class Server {
    public static void main(String[] args) {
        try {
            // Creamos e inicializamos el ORB
            ORB orb = ORB.init(args, null);

            // Obtenemos el Root POA y lo activamos
            POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootPOA.the_POAManager().activate();

            // Creamos la implementación (servant) y le registramos el ORB
            HelloServiceServant helloServant = new HelloServiceServant();
            helloServant.setORB(orb);

            // Obtenemos una referencia desde el servant
            org.omg.CORBA.Object ref = rootPOA.servant_to_reference(helloServant);
            HelloService href = HelloServiceHelper.narrow(ref);

            // Obtenemos una referencia al NameService
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");

            // Resolvemos la referencia para obtener un NamingContextExt
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // Asociamos "Hello" a nuestra implementación
            NameComponent[] path = ncRef.to_name("Hello");
            ncRef.rebind(path, href);

            System.out.println("Server up and running...");

            // Esperamos por conexiones
            orb.run();
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        }
    }
}
