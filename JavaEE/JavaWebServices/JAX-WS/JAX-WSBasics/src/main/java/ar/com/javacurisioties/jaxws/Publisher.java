package ar.com.javacurisioties.jaxws;

import ar.com.javacurisioties.jaxws.services.HelloWorldDocument;
import ar.com.javacurisioties.jaxws.services.HelloWorldDocumentBare;
import ar.com.javacurisioties.jaxws.services.HelloWorldImpl;
import ar.com.javacurisioties.jaxws.services.HelloWorldRPC;

import javax.xml.ws.Endpoint;

/**
 * Existen dos approach a la hora de crear un webservice.
 *
 * Top-down: Se empieza por definir el contrato WSDL y se generan las clases Java desde el mismo.
 * Bottom-up: Se definen las clases Java y luego el contrato WSDL se genera en base a esas clases.
 *
 * En este ejemplo usaremos el approach "Bottom-up" para esto debemos arrancar definiendo el
 * SEI (Service Endpoint Interface).
 *
 * Luego de exponer el servicio podemos obtener el WSDL en el base path usando ?wsdl
 */
public class Publisher {
    public static void main(String[] args) {
        Endpoint.publish("http://127.0.0.1:9911/helloWorldRPC", new HelloWorldRPC());
        Endpoint.publish("http://127.0.0.1:9922/helloWorldDocument", new HelloWorldDocument());
        Endpoint.publish("http://127.0.0.1:9933/helloWorldDocumentBare", new HelloWorldDocumentBare());
        Endpoint.publish("http://127.0.0.1:9944/helloWorldImpl", new HelloWorldImpl());

    }
}
