package ar.com.javacuriosities.hibernate.issues.deadlock;

import ar.com.javacuriosities.hibernate.issues.deadlock.model.Customer;
import ar.com.javacuriosities.hibernate.issues.deadlock.model.Profile;
import ar.com.javacuriosities.hibernate.issues.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Algunos queries utiles para ver los locks en MySQL.
 *
 * SELECT * FROM information_schema.innodb_trx;
 *
 * SELECT
 * waiting_trx_id,
 * waiting_pid,
 * waiting_query,
 * blocking_trx_id,
 * blocking_pid,
 * blocking_query
 * FROM sys.innodb_lock_waits;
 */
public class Main {
    public static void main(String[] args) {
        // Obtenemos una Session Factory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Profile profile = new Profile();
        Customer customer = new Customer();

        try (Session session1 = sessionFactory.openSession()) {
            session1.beginTransaction();

            profile.setDescription("Profile Description");

            session1.saveOrUpdate(profile);

            try (Session session2 = sessionFactory.openSession()) {
                session2.beginTransaction();

                customer.setProfile(profile);
                customer.setName("Unknown Customer");

                session2.saveOrUpdate(customer);

                session2.getTransaction().commit();
            } catch (Exception e) {
                // Log and Handle exception
                e.printStackTrace();
            }

            session1.getTransaction().commit();
        }

    }
}
