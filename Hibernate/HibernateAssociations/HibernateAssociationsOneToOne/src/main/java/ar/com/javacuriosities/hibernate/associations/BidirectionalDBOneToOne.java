package ar.com.javacuriosities.hibernate.associations;

import ar.com.javacuriosities.hibernate.model.bidirectional_db.ContactInformation;
import ar.com.javacuriosities.hibernate.model.bidirectional_db.Employee;
import ar.com.javacuriosities.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BidirectionalDBOneToOne {

    public static void main(String[] args) {
        // Obtenemos una Session Factory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        // Abrimos y trabajamos sobre una Session
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Employee employee = new Employee();
            employee.setName("Cosme Fulanito");

            ContactInformation contactInformation = new ContactInformation();
            contactInformation.setComments("Contact Information");

            employee.setContactInformation(contactInformation);
            contactInformation.setEmployee(employee);

            session.save(employee);
            session.save(contactInformation);

            session.getTransaction().commit();
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
