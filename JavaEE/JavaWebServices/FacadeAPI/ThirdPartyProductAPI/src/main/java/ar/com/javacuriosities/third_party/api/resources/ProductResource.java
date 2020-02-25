package ar.com.javacuriosities.third_party.api.resources;

import ar.com.javacuriosities.third_party.api.model.Product;
import ar.com.javacuriosities.third_party.api.repository.ProductRepository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("products")
public class ProductResource {

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Product> getProducts() {
        return ProductRepository.getProducts();
    }
}
