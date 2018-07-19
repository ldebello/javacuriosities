package ar.com.javacuriosities.spring.dao;

import ar.com.javacuriosities.spring.model.Person;

import java.util.List;

public interface PersonDAO {
    Person getPersonById(Long id);

    List<Person> getAllPersons();

    boolean delete(Person person);

    boolean update(Person person);

    boolean create(Person person);
}