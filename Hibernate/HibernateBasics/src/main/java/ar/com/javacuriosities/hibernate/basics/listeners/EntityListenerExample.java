package ar.com.javacuriosities.hibernate.basics.listeners;

import ar.com.javacuriosities.hibernate.model.Address;
import ar.com.javacuriosities.hibernate.model.Person;
import ar.com.javacuriosities.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class EntityListenerExample {

    public static void main(String[] args) {
        // Obtenemos una Session Factory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        // Abrimos y trabajamos sobre una Session
        try (Session session = sessionFactory.openSession()) {
            Address address = new Address("Street", "City", "1234", "State");
            Person person = new Person("Cosme Fulanito", address);

            // Iniciamos una transacción
            Transaction tx = session.beginTransaction();

            session.save(person);

            person.setName("Sancho Panza");

            tx.commit();

            List<Person> people = session.createQuery("from people_entity").list();

            for (Person currentPerson : people) {
                System.out.println(currentPerson);
            }
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
