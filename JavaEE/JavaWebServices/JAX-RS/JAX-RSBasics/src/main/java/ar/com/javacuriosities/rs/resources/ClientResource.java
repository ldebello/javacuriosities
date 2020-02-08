package ar.com.javacuriosities.rs.resources;

import ar.com.javacuriosities.rs.model.Client;
import ar.com.javacuriosities.rs.repository.ClientRepository;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("clients")
public class ClientResource {

    @GET
    @Path("default")
    @Produces(MediaType.TEXT_HTML)
    public String getClientTextHTML() {
        return "Hola";
    }


    @GET
    @Path("default")
    @Produces(MediaType.TEXT_XML)
    public String getClientTextXML() {
        return "<?xml version=\"1.0\"?>" + "<client>Hello Client" + "</client>";
    }

    @GET
    @Path("default")
    @Produces(value = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Client getClient() {
        List<Client> clients = ClientRepository.getClients();
        return !clients.isEmpty() ? clients.get(0) : null;
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Client getClientById(@PathParam("id") String id) {
        Client client = ClientRepository.getClient(Integer.parseInt(id));
        if (client == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("Client not found").type(MediaType.TEXT_PLAIN).build());
        }
        return client;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Client> getClients() {
        return ClientRepository.getClients();
    }

    @GET
    @Path("search/{query}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Client> getClientByName(@PathParam("query") String name) {
        return ClientRepository.getClientByName(name);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Client create(Client client) {
        return ClientRepository.saveClient(client);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Client update(Client client) {
        return ClientRepository.updateClient(client);
    }

    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void remove(@PathParam("id") int id) {
        ClientRepository.deleteClient(id);
    }

    @POST
    @Path("add")
    public Response addClient(@FormParam("name") String name, @FormParam("age") int age) {
        Client client = new Client();
        client.setName(name);
        client.setAge(age);
        ClientRepository.saveClient(client);
        return Response.status(200).entity("Client created, Name: " + name + ", Age: " + age).build();
    }
}
