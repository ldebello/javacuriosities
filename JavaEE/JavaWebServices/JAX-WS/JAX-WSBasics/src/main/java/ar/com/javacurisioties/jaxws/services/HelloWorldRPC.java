package ar.com.javacurisioties.jaxws.services;

import ar.com.javacurisioties.jaxws.parameters.SayParameters;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class HelloWorldRPC {

    public void say0(String name) {
        System.out.println("Hello " + name);
    }

    public String say1(String name) {
        return "Hello " + name;
    }

    public String say2(String name, String message) {
        return message + " " + name;
    }

    public String say3(SayParameters parameters) {
        return parameters.getMessage() + " " + parameters.getName();
    }
}
