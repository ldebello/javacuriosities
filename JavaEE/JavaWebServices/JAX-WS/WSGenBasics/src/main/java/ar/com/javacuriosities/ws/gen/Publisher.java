package ar.com.javacuriosities.ws.gen;

import ar.com.javacuriosities.ws.gen.service.ServerInfo;

import javax.xml.ws.Endpoint;

public class Publisher {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/ws/server", new ServerInfo());

        System.out.println("Service is published!");
    }
}
