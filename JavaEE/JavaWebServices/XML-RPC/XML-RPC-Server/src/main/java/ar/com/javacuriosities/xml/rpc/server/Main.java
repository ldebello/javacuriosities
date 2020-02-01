package ar.com.javacuriosities.xml.rpc.server;

import ar.com.javacuriosities.xml.rpc.server.handlers.Students;
import ar.com.javacuriosities.xml.rpc.server.handlers.Weather;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.webserver.WebServer;

/**
 * XML-RPC es un protocolo de llamada a procedimiento remoto que usa XML para codificar los datos y HTTP como protocolo de transmisión de mensajes.
 *
 * Especificación: http://xmlrpc.scripting.com/spec.html
 */
public class Main {

    private static final int PORT = 8580;

    public static void main(String[] args) throws Exception {
        // Instanciamos el WebServer (Servidor HTTP)
        WebServer webServer = new WebServer(PORT);

        // Servidor XML RPC Multithread
        XmlRpcServer xmlRpcServer = webServer.getXmlRpcServer();

        // Property handler para exponer los servicios (HandlerName - Class)
        PropertyHandlerMapping phm = new PropertyHandlerMapping();

        // Registramos handlers
        System.out.println("Registering Weather Handler");
        phm.addHandler(Weather.class.getSimpleName(), Weather.class);

        System.out.println("Registering Students Handler");
        phm.addHandler(Students.class.getSimpleName(), Students.class);

        // Asignamos los handlers al server XML RPC
        xmlRpcServer.setHandlerMapping(phm);

        // Iniciamos el servidor web
        webServer.start();
        System.out.println("Server up and running");
    }
}
