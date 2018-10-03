package ar.com.javacuriosities.hibernate;

import ar.com.javacuriosities.hibernate.model.Event;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = null;
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("MyPersistenceUnit");

            EntityManager entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();
            entityManager.persist(new Event("First Event", new Date()));
            entityManager.persist(new Event("Second Event", new Date()));
            entityManager.getTransaction().commit();

            entityManager.close();

            entityManager = entityManagerFactory.createEntityManager();
            List<Event> result = entityManager.createQuery("from Event", Event.class).getResultList();
            for (Event event : result) {
                System.out.println("Event (" + event.getDate() + ") : " + event.getTitle());
            }
            entityManager.close();
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        } finally {
            if (entityManagerFactory != null) {
                entityManagerFactory.close();
            }
        }
    }
}
