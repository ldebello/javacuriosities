package ar.com.javacurisioties.jaxws.client;

import ar.com.javacurisioties.jaxws.sei.HelloWorld;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.handler.MessageContext;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * En este ejemplo vemos un cliente muy simple el cual depende de la SEI.
 */
public class Main {

    private static final String WS_URL = "http://127.0.0.1:9944/helloWorldImpl?wsdl";

    private static final String SERVICE_NAME = "HelloWorldImplService";
    private static final String TARGET_NAMESPACE = "http://impl.sei.jaxws.javacurisioties.com.ar/";

    public static void main(String[] args) throws Exception {

        URL url = new URL(WS_URL);
        QName qname = new QName(TARGET_NAMESPACE, SERVICE_NAME);

        Service service = Service.create(url, qname);
        HelloWorld hello = service.getPort(HelloWorld.class);

        // Podemos enviar HTTP Headers
        Map<String, Object> requestContext = ((BindingProvider) hello).getRequestContext();
        requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, WS_URL);

        Map<String, List<String>> httpHeaders = new HashMap<>();
        httpHeaders.put("HeaderName", Collections.singletonList("HeaderValue"));

        requestContext.put(MessageContext.HTTP_REQUEST_HEADERS, httpHeaders);

        System.out.println(hello.say1("Cosme Fulanito"));
    }
}
