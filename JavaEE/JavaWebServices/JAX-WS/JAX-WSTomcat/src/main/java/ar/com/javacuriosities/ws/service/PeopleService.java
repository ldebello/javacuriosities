package ar.com.javacuriosities.ws.service;

import ar.com.javacuriosities.ws.model.Person;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.ResponseWrapper;
import java.util.List;

@WebService
public interface PeopleService {

    @WebMethod
    boolean add(Person p);

    @WebMethod
    boolean delete(int id);

    @WebMethod
    Person get(int id);

    @WebMethod
    @WebResult(name = "person")
    @ResponseWrapper(localName = "getAllPeopleResponse", targetNamespace = "http://service.ws.javacuriosities.com.ar/")
    List<Person> getAll();
}
