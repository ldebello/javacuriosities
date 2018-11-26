package ar.com.javacuriosities.hibernate.basics.mapped_superclass;

import ar.com.javacuriosities.hibernate.model.User;
import ar.com.javacuriosities.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MappedSuperclassExample {

    public static void main(String[] args) throws IOException {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try {
            try (Session session = sessionFactory.openSession()) {

                User user = new User();
                user.setName("Cosme Fulanito");
                user.setPassword("1234");

                Transaction tx = session.beginTransaction();

                session.saveOrUpdate(user);

                tx.commit();
            } catch (Exception e) {
                // Log and Handle exception
                e.printStackTrace();
            }

            Thread.sleep(TimeUnit.SECONDS.toMillis(5));

            try (Session session = sessionFactory.openSession()) {

                Transaction tx = session.beginTransaction();

                User user = (User) session.get(User.class, 1L);

                user.setPassword("SecuredPassword-1234");

                session.saveOrUpdate(user);

                tx.commit();
            } catch (Exception e) {
                // Log and Handle exception
                e.printStackTrace();
            }
        } catch (InterruptedException e) {
            // Log and Handle exception
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
