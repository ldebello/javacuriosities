package ar.com.javacuriosities.spring.dao;

import ar.com.javacuriosities.spring.model.Person;
import ar.com.javacuriosities.spring.model.utis.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class PersonDAOImpl implements PersonDAO {

    private static final String SQL_FIND_PERSON = "SELECT * FROM people WHERE id = ?";
    private static final String SQL_DELETE_PERSON = "DELETE FROM people WHERE id = ?";
    private static final String SQL_UPDATE_PERSON = "UPDATE people SET first_name = ?, last_name = ?, age  = ? WHERE id = ?";
    private static final String SQL_GET_ALL = "SELECT * FROM people";
    private static final String SQL_INSERT_PERSON = "INSERT INTO people(first_name, last_name, age) VALUES (?,?,?)";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Person getPersonById(Long id) {
        return jdbcTemplate.queryForObject(SQL_FIND_PERSON, new Object[]{id}, new PersonMapper());
    }

    public List<Person> getAllPersons() {
        return jdbcTemplate.query(SQL_GET_ALL, new PersonMapper());
    }

    public boolean delete(Person person) {
        return jdbcTemplate.update(SQL_DELETE_PERSON, person.getId()) > 0;
    }

    public boolean update(Person person) {
        return jdbcTemplate.update(SQL_UPDATE_PERSON, person.getFirstName(), person.getLastName(), person.getAge(),
                person.getId()) > 0;
    }

    public boolean create(Person person) {
        return jdbcTemplate.update(SQL_INSERT_PERSON, person.getFirstName(), person.getLastName(),
                person.getAge()) > 0;
    }
}

