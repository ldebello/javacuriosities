package ar.com.javacuriosities.third_party.api;

import ar.com.javacuriosities.third_party.api.resources.ProductResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/*
 * http://localhost:8082/third-party/api/products
 */
@ApplicationPath("api")
public class ThirdPartyApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(ProductResource.class);

        return resources;
    }
}