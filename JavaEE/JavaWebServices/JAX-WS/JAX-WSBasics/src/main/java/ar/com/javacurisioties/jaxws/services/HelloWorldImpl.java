package ar.com.javacurisioties.jaxws.services;

import ar.com.javacurisioties.jaxws.parameters.SayParameters;
import ar.com.javacurisioties.jaxws.sei.HelloWorld;

import javax.jws.WebService;

@WebService(endpointInterface = "ar.com.javacurisioties.jaxws.sei.HelloWorld")
public class HelloWorldImpl implements HelloWorld {
    @Override
    public void say0(String name) {
        System.out.println("Hello " + name);
    }

    @Override
    public String say1(String name) {
        return "Hello " + name;
    }

    @Override
    public String say2(String name, String message) {
        return message + " " + name;
    }

    @Override
    public String say3(SayParameters parameters) {
        return parameters.getMessage() + " " + parameters.getName();
    }
}
