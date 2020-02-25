package ar.com.javacuriosities.facade.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

/*
 * http://localhost:8080/products
 */
@SpringBootApplication
public class ProductAPIApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductAPIApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        /*
         * Siempre que usemos clientes HTTP es util configurar el connection and read timeout.
         */
        return restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(2))
                .setReadTimeout(Duration.ofSeconds(4))
                .build();
    }
}