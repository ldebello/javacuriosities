package ar.com.javacurisioties.jaxws.schema_validation;

import com.sun.xml.internal.ws.developer.SchemaValidation;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;

@WebService
@SchemaValidation
public class HelloWorldSchemaValidation {

    @WebMethod(operationName = "simpleQuery")
    public void without_wrapper(Parameters parameters) {
        System.out.println("Code: " + parameters.getCode());
        System.out.println("Start: " + parameters.getStart());
        System.out.println("End: " + parameters.getEnd());
    }

    @WebMethod(operationName = "wrapperQuery")
    @RequestWrapper(localName = "parameters", className = "ar.com.javacurisioties.jaxws.schema_validation.Parameters")
    public void wrapper(
            @WebParam(name = "code") String code,
            @WebParam(name = "start") int start,
            @WebParam(name = "end") int end) {
        System.out.println("Code: " + code);
        System.out.println("Start: " + start);
        System.out.println("End: " + end);
    }
}
