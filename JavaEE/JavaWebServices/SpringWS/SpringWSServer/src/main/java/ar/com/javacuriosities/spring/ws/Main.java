package ar.com.javacuriosities.spring.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Podemos encontrar el WSDL en la siguiente URL http://localhost:8080/ws/countries.wsdl
 */
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}