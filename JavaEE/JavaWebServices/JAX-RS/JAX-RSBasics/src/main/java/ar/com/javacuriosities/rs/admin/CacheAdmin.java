package ar.com.javacuriosities.rs.admin;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;

public class CacheAdmin {

    @GET
    @RolesAllowed("ADMIN")
    public String read() {
        return "Cached Data";
    }

    @POST
    public Response refresh() {
        System.out.println("Refreshing cache");
        return Response.accepted().build();
    }

    @DELETE
    public Response evict() {
        System.out.println("Evicting cache");
        return Response.noContent().build();
    }
}
