package ar.com.javacuriosities.spring;

import ar.com.javacuriosities.spring.config.ApplicationConfig;
import ar.com.javacuriosities.spring.dao.PersonDAO;
import ar.com.javacuriosities.spring.model.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.security.SecureRandom;

public class Main {
    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        PersonDAO dao = context.getBean(PersonDAO.class);

        System.out.println("***** Creating person *****");
        Person newPerson = new Person(99, "Cosme", "Fulanito - " + random.nextInt(Integer.MAX_VALUE));
        System.out.println(newPerson);
        dao.create(newPerson);

        System.out.println("***** List of people *****");
        for (Person person : dao.getAllPersons()) {
            System.out.println(person);
        }
    }
}
