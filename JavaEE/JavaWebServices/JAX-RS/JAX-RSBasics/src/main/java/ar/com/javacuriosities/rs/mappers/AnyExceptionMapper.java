package ar.com.javacuriosities.rs.mappers;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AnyExceptionMapper implements ExceptionMapper<Exception> {

    @Context
    private UriInfo context;

    @Override
    public Response toResponse(Exception ex) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).type(
                MediaType.APPLICATION_JSON_TYPE)
                .entity(String.format("An error occurred that prevented completing your request. (Path: %s - Message: %s)", context.getPath(), ex.getMessage())).build();
    }
}