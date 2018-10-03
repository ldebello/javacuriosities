package ar.com.javacuriosities.hibernate;

import ar.com.javacuriosities.hibernate.model.Event;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        SessionFactory sessionFactory = null;
        try {
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure()
                    .build();

            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

            try (Session session = sessionFactory.openSession()) {
                session.beginTransaction();
                session.save(new Event("First Event", new Date()));
                session.save(new Event("Second Event", new Date()));
                session.getTransaction().commit();
            }

            try (Session session = sessionFactory.openSession()) {
                List<Event> result = session.createQuery("from Event").list();
                for (Event event : result) {
                    System.out.println("Event (" + event.getDate() + ") : " + event.getTitle());
                }
            }
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        } finally {
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }
    }
}
