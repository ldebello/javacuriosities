package ar.com.javacuriosities.hibernate.associations;

import ar.com.javacuriosities.hibernate.model.bidirectional.Student;
import ar.com.javacuriosities.hibernate.model.bidirectional.Summary;
import ar.com.javacuriosities.hibernate.model.unidirectional.Address;
import ar.com.javacuriosities.hibernate.model.unidirectional.Client;
import ar.com.javacuriosities.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BidirectionalOneToOne {

    public static void main(String[] args) {
        // Obtenemos una Session Factory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        // Abrimos y trabajamos sobre una Session
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Student student = new Student();
            student.setName("Cosme Fulanito");

            Summary summary = new Summary();
            summary.setComments("Nice guy");

            student.setSummary(summary);
            summary.setStudent(student);

            session.save(summary);
            session.save(student);

            session.getTransaction().commit();
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
