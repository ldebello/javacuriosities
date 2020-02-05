package ar.com.javacuriosities.ws.service;

import ar.com.javacuriosities.ws.model.Person;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface PersonService {

    @WebMethod
    boolean add(Person p);

    @WebMethod
    boolean delete(int id);

    @WebMethod
    Person get(int id);

    @WebMethod
    List<Person> getAll();
}
