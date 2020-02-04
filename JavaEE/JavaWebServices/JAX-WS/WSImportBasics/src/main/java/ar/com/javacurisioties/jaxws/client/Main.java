package ar.com.javacurisioties.jaxws.client;

import ar.com.javacurisioties.jaxws.client.gen.HelloWorld;
import ar.com.javacurisioties.jaxws.client.gen.HelloWorldImplService;

public class Main {

    public static void main(String[] args) {
        HelloWorldImplService service = new HelloWorldImplService();

        HelloWorld helloWorldImplPort = service.getHelloWorldImplPort();

        System.out.println(helloWorldImplPort.say("Cosme Fulanito"));
    }
}
