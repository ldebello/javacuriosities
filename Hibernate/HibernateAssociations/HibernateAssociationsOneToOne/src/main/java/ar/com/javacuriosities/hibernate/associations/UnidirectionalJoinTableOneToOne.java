package ar.com.javacuriosities.hibernate.associations;

import ar.com.javacuriosities.hibernate.model.unidirectional_join_table.Task;
import ar.com.javacuriosities.hibernate.model.unidirectional_join_table.TaskDetail;
import ar.com.javacuriosities.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class UnidirectionalJoinTableOneToOne {

    public static void main(String[] args) {
        // Obtenemos una Session Factory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        // Abrimos y trabajamos sobre una Session
        try (Session session = sessionFactory.openSession()) {
            Task task = new Task();
            task.setDescription("Initial Task");

            TaskDetail details = new TaskDetail();
            details.setDetails("Custom Details");

            task.setDetails(details);

            session.beginTransaction();

            session.save(task);

            session.getTransaction().commit();
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
