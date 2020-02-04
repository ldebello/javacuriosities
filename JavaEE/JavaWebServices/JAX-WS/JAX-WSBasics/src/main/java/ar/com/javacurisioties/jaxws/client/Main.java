package ar.com.javacurisioties.jaxws.client;

import ar.com.javacurisioties.jaxws.sei.HelloWorld;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

/**
 * En este ejemplo vemos un cliente muy simple el cual depende de la SEI.
 */
public class Main {

    private static final String WS_URL = "http://127.0.0.1:9944/helloWorldImpl?wsdl";

    private static final String SERVICE_NAME = "HelloWorldImplService";
    private static final String TARGET_NAMESPACE = "http://services.jaxws.javacurisioties.com.ar/";

    public static void main(String[] args) throws Exception {

        URL url = new URL(WS_URL);
        QName qname = new QName(TARGET_NAMESPACE, SERVICE_NAME);

        Service service = Service.create(url, qname);
        HelloWorld hello = service.getPort(HelloWorld.class);

        System.out.println(hello.say1("Cosme Fulanito"));
    }
}
