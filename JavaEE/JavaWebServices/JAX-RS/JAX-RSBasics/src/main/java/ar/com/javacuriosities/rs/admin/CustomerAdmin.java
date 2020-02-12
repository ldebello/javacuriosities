package ar.com.javacuriosities.rs.admin;

import javax.ws.rs.DELETE;
import javax.ws.rs.core.Response;

public class CustomerAdmin {

    @DELETE
    public Response remove() {
        System.out.println("Deleting all customer");
        return Response.noContent().build();
    }
}
