package ar.com.javacuriosities.hibernate.associations;

import ar.com.javacuriosities.hibernate.model.bidirectional_shared_pk.Author;
import ar.com.javacuriosities.hibernate.model.bidirectional_shared_pk.Biography;
import ar.com.javacuriosities.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BidirectionalSharedPKOneToOne {

    public static void main(String[] args) {
        // Obtenemos una Session Factory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        // Abrimos y trabajamos sobre una Session
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Biography biography = new Biography();
            biography.setInformation("This is the most important author");

            Author author1 = new Author();
            author1.setName("Cosme Fulanito III");

            Author author2 = new Author();
            author2.setName("Cosme Fulanito IX");

            Author author3 = new Author();
            author3.setName("Cosme Fulanito VII");

            biography.setAuthor(author2);
            author2.setBiography(biography);

            session.save(author1);
            session.save(author2);
            session.save(author3);

            session.getTransaction().commit();
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
