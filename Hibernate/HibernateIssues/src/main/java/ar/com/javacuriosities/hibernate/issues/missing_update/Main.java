package ar.com.javacuriosities.hibernate.issues.missing_update;

import ar.com.javacuriosities.hibernate.issues.missing_update.model.User;
import ar.com.javacuriosities.hibernate.issues.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;

public class Main {
    public static void main(String[] args) {
        // Obtenemos una Session Factory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        User user = new User();

        try (Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            user.setName("Cosme");
            user.setCounter(1L);

            session.saveOrUpdate(user);

            session.getTransaction().commit();
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        }


        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            User userFromDb = session.get(User.class, user.getId());

            StatelessSession statelessSession = sessionFactory.openStatelessSession();

            String hqlUpdate = "UPDATE User user SET user.counter = :newCounter WHERE user.id = :userId AND (user.counter = :counter OR user.counter IS NULL)";

            statelessSession.beginTransaction();
            try {
                int rowsUpdated = statelessSession
                        .createQuery(hqlUpdate)
                        .setParameter("counter", userFromDb.getCounter())
                        .setParameter("newCounter", 2L)
                        .setParameter("userId", userFromDb.getId())
                        .executeUpdate();

                System.out.println("Updated: " + rowsUpdated);

                statelessSession.getTransaction().commit();
            } finally {
                statelessSession.close();
            }

            // userFromDb.setName("Invalid Name");

            session.getTransaction().commit();
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        }
    }
}
