package ar.com.javacurisioties.jaxws.client;

public class Main {

    public static void main(String[] args) {
        HelloWorldImplService service = new HelloWorldImplService();

        HelloWorld helloWorldImplPort = service.getHelloWorldImplPort();

        System.out.println(helloWorldImplPort.say("Cosme Fulanito"));
    }
}
