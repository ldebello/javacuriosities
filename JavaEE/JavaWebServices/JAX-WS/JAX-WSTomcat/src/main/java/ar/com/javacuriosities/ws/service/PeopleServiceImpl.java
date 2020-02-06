package ar.com.javacuriosities.ws.service;

import ar.com.javacuriosities.ws.model.Person;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebService(endpointInterface = "ar.com.javacuriosities.ws.service.PeopleService")
public class PeopleServiceImpl implements PeopleService {

    private static Map<Integer, Person> persons = new HashMap<>();

    @Override
    public boolean add(Person p) {
        if (persons.get(p.getId()) != null) {
            return false;
        }

        persons.put(p.getId(), p);
        return true;
    }

    @Override
    public boolean delete(int id) {
        if (persons.get(id) == null) {
            return false;
        }

        persons.remove(id);
        return true;
    }

    @Override
    public Person get(int id) {
        return persons.get(id);
    }

    @Override
    public List<Person> getAll() {
        return new ArrayList<>(persons.values());
    }

}