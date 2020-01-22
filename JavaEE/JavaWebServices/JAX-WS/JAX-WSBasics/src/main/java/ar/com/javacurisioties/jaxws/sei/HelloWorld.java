package ar.com.javacurisioties.jaxws.sei;

import ar.com.javacurisioties.jaxws.parameters.SayParameters;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Una forma muy común de exponer un WS es por medio de una SEI (Service Endpoint Interface)
 * Lo cual nos permite definir las annotations a nivel de interfaz y luego proveer la implementación.
 */
@WebService
public interface HelloWorld {

    @Oneway
    void say0(String name);

    @WebMethod(operationName = "say")
    String say1(@WebParam(name = "name") String name);

    @WebMethod(exclude = true)
    String say2(String name, String message);

    String say3(@WebParam(name = "parameters") SayParameters parameters);
}
