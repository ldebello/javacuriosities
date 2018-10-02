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
                session.save(new Event("Nuestro primer evento", new Date()));
                session.save(new Event("Un segundo evento", new Date()));
                session.getTransaction().commit();
            }

            try (Session session = sessionFactory.openSession()) {
                session.beginTransaction();
                List<Event> result = session.createQuery("from Event").list();
                for (Event event : result) {
                    System.out.println("Event (" + event.getDate() + ") : " + event.getTitle());
                }
                session.getTransaction().commit();
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
