package ar.com.javacuriosities.rs;

import ar.com.javacuriosities.rs.model.Customer;
import org.glassfish.jersey.client.ClientProperties;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        client.property(ClientProperties.CONNECT_TIMEOUT, 2000);
        client.property(ClientProperties.READ_TIMEOUT, 4000);

        WebTarget baseTarget = client.target("http://localhost:8080/JAX-RSBasics/api");

        WebTarget customersTarget = baseTarget.path("customers");

        Invocation.Builder request = customersTarget.request(MediaType.APPLICATION_JSON);

        Response response = request.get(Response.class);

        System.out.println("*** Response Code ***");
        System.out.println(response.getStatus());

        List<Customer> customers = response.readEntity(new GenericType<List<Customer>>() {
        });

        System.out.println("*** Customers ***");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }
}
