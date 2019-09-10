package ar.com.javacuriosities.hibernate.issues.flush_order;

import ar.com.javacuriosities.hibernate.issues.flush_order.model.Book;
import ar.com.javacuriosities.hibernate.issues.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Cada cambio de state genera una accion que es encolada en el contexto persistente. Estas son ejecutadas en el orden establecido por en la clase ActionQueue.
 *
 * OrphanRemovalAction
 * AbstractEntityInsertAction
 * EntityUpdateAction
 * QueuedOperationCollectionAction
 * CollectionRemoveAction
 * CollectionUpdateAction
 * CollectionRecreateAction
 * EntityDeleteAction
 */
public class Main {
    public static void main(String[] args) {
        // Obtenemos una Session Factory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Book book = new Book();

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            book.setTitle("Hibernate in Action");
            book.setIsbn("193239415x");

            session.saveOrUpdate(book);

            session.getTransaction().commit();
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        }

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Book bookFromDB = session.load(Book.class, book.getId());
            session.remove(bookFromDB);

            Book newBook = new Book();
            newBook.setTitle("Hibernate in Action Book");
            newBook.setIsbn("193239415x");

            session.saveOrUpdate(newBook);

            session.getTransaction().commit();
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        }

    }
}
