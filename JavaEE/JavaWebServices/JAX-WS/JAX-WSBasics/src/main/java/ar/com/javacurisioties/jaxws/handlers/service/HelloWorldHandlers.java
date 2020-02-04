package ar.com.javacurisioties.jaxws.handlers.service;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

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
