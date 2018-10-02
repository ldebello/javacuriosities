package ar.com.javacuriosities.hibernate;

import ar.com.javacuriosities.hibernate.model.Event;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = null;
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory( "MyPersistenceUnit" );

            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist( new Event( "Nuestro primer evento", new Date() ) );
            entityManager.persist( new Event( "Un segundo evento", new Date() ) );
            entityManager.getTransaction().commit();
            entityManager.close();

            // now lets pull events from the database and list them
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            List<Event> result = entityManager.createQuery( "from Event", Event.class ).getResultList();
            for ( Event event : result ) {
                System.out.println( "Event (" + event.getDate() + ") : " + event.getTitle() );
            }
            entityManager.getTransaction().commit();
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
