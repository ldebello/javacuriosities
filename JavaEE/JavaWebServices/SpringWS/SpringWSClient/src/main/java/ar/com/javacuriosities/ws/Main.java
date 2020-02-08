package ar.com.javacuriosities.ws;


import ar.com.javacuriosities.consumingwebservice.wsdl.GetCountryResponse;
import ar.com.javacuriosities.ws.clients.CountryClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    CommandLineRunner lookup(CountryClient countryClient) {
        return args -> {
            String country = "Spain";

            if (args.length > 0) {
                country = args[0];
            }

            GetCountryResponse response = countryClient.getCountry(country);
            System.out.println("Currency: " + response.getCountry().getCurrency());
        };
    }

}