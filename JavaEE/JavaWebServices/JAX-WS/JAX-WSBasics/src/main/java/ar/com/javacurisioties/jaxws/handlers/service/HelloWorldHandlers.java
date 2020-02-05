package ar.com.javacurisioties.jaxws.handlers.service;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/*
 * En este ejemplo este se puede ver como los Handlers son ejecutados cuando se recibe un mensaje
 *
 * - Cuando el mensaje llega se ejecutan todos los Handler del tipo SOAPHandler
 * - Luego se procesan todos los Handler del tipo LogicalHandler
 * - Luego al enviar la respuesta los handlers son procesados nuevamente pero de manera inversa
 *
 * SOAPHandler:
 * Nos permiten interactuar con el mensaje SOAP
 *
 * LogicalHandler:
 * Solo nos permiten interactuar con el SOAP Payload (O sea el mensaje dentro del SOAP Body)
 *
 */
@WebService
@HandlerChain(file = "handlers.xml")
public class HelloWorldHandlers {

    public int simpleOperation(int valor1, int valor2) {
        return valor1 + valor2;
    }

    @WebMethod(operationName = "complexOperation")
    public double complexOperation(@WebParam(name = "data") double data) {
        System.out.println("Processing data for complex operation");
        return data * 1.0;
    }
}
