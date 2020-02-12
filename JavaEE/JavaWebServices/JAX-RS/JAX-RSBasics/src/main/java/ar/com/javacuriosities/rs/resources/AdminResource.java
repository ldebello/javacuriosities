package ar.com.javacuriosities.rs.resources;

import ar.com.javacuriosities.rs.admin.CacheAdmin;
import ar.com.javacuriosities.rs.admin.CustomerAdmin;

import javax.ws.rs.Path;

@Path("admin")
public class AdminResource {

    @Path("customer")
    public CustomerAdmin admin() {
        return new CustomerAdmin();
    }

    @Path("cache")
    public CacheAdmin cache() {
        return new CacheAdmin();
    }
}
