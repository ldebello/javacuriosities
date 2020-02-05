package ar.com.javacurisioties.jaxws.bindings;

import ar.com.javacurisioties.jaxws.parameters.SayParameters;

import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

@WebService
@BindingType(value = SOAPBinding.SOAP12HTTP_BINDING)
public class HelloWorldSOAP12 {

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
