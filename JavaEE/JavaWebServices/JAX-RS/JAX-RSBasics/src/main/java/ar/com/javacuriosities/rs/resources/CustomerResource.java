package ar.com.javacuriosities.rs.resources;

import ar.com.javacuriosities.rs.exceptions.InvalidCustomerException;
import ar.com.javacuriosities.rs.model.Customer;
import ar.com.javacuriosities.rs.repository.CustomerRepository;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("customers")
public class CustomerResource {

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Customer> getCustomers() {
        return CustomerRepository.getCustomers();
    }


    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Customer getCustomerById(@PathParam("id") int id) {
        return CustomerRepository.getCustomer(id);
    }

    @GET
    @Path("search")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Customer> getCustomerByName(@QueryParam("name") String name) {
        return CustomerRepository.getCustomerByName(name);
    }

    @GET
    @Path("search/{name}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Customer> searchByName(@PathParam("name") String name) {
        return CustomerRepository.getCustomerByName(name);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Customer create(Customer customer) {
        if (customer.getAge() < 18) {
            throw new InvalidCustomerException("Customer cannot be under-age");
        }
        return CustomerRepository.saveCustomer(customer);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Customer update(Customer customer) {
        if (customer.getId() == null) {
            throw new RuntimeException("Id is required");
        }
        return CustomerRepository.updateCustomer(customer);
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") int id) {
        CustomerRepository.deleteCustomer(id);
        return Response.noContent().build();
    }

    @POST
    @Path("add")
    public Response addClient(@FormParam("name") String name, @FormParam("age") int age) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setAge(age);
        CustomerRepository.saveCustomer(customer);
        return Response.status(200).entity("Customer created, Name: " + name + ", Age: " + age).build();
    }
}
