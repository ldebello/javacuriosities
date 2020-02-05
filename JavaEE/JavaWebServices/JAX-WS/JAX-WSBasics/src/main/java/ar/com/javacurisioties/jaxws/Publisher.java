package ar.com.javacurisioties.jaxws;

import ar.com.javacurisioties.jaxws.bindings.HelloWorldDocument;
import ar.com.javacurisioties.jaxws.bindings.HelloWorldDocumentBare;
import ar.com.javacurisioties.jaxws.bindings.HelloWorldRPC;
import ar.com.javacurisioties.jaxws.faults.HelloWorldFaults;
import ar.com.javacurisioties.jaxws.handlers.service.HelloWorldHandlers;
import ar.com.javacurisioties.jaxws.http_headers.HelloWorldHTTPHeaders;
import ar.com.javacurisioties.jaxws.mtom.service.HelloWorldMTOMImpl;
import ar.com.javacurisioties.jaxws.sei.impl.HelloWorldImpl;

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
        // Bindings
        Endpoint.publish("http://127.0.0.1:9911/helloWorldRPC", new HelloWorldRPC());
        Endpoint.publish("http://127.0.0.1:9922/helloWorldDocument", new HelloWorldDocument());
        Endpoint.publish("http://127.0.0.1:9933/helloWorldDocumentBare", new HelloWorldDocumentBare());

        // SEI
        Endpoint.publish("http://127.0.0.1:9944/helloWorldImpl", new HelloWorldImpl());

        // Headers
        Endpoint.publish("http://127.0.0.1:9955/HelloWorldHTTPHeaders", new HelloWorldHTTPHeaders());

        // Faults
        Endpoint.publish("http://127.0.0.1:9966/HelloWorldFaults", new HelloWorldFaults());

        // Handlers
        Endpoint.publish("http://127.0.0.1:9977/HelloWorldHandlers", new HelloWorldHandlers());

        // MTOM
        Endpoint.publish("http://127.0.0.1:9988/HelloWorldMTOM", new HelloWorldMTOMImpl());
    }
}
