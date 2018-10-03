package ar.com.javacuriosities.hibernate;

import ar.com.javacuriosities.hibernate.model.Event;
import ar.com.javacuriosities.hibernate.model.EventHistory;
import ar.com.javacuriosities.hibernate.model.EventProperty;
import ar.com.javacuriosities.hibernate.model.EventSubscriber;
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

                Event event = new Event("First Event", new Date());
                event.getEventData().setUser("Cosme Fulanito");

                EventHistory eventHistory1 = new EventHistory(new Date(), "Initial Version");
                EventHistory eventHistory2 = new EventHistory(new Date(), "Second Version");

                event.getEventHistory().add(eventHistory1);
                event.getEventHistory().add(eventHistory2);

                EventSubscriber eventSubscriber1 = new EventSubscriber("Pablo Marmol");
                EventSubscriber eventSubscriber2 = new EventSubscriber("Pedro Picapiedra");

                event.getEventSubscribers().add(eventSubscriber1);
                event.getEventSubscribers().add(eventSubscriber2);

                EventProperty eventProperty1 = new EventProperty("First Property");
                EventProperty eventProperty2 = new EventProperty("Second Property");

                event.getEventProperties().put("Property1", eventProperty1);
                event.getEventProperties().put("Property2", eventProperty2);

                session.save(event);
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
