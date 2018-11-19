package ar.com.javacuriosities.hibernate.associations;

import ar.com.javacuriosities.hibernate.model.unidirectional_shared_pk.Task;
import ar.com.javacuriosities.hibernate.model.unidirectional_shared_pk.TaskMetadata;
import ar.com.javacuriosities.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class UnidirectionalSharedPKOneToOne {

    public static void main(String[] args) {
        // Obtenemos una Session Factory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        // Abrimos y trabajamos sobre una Session
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Task task = new Task();
            task.setDescription("First Task");

            TaskMetadata taskMetadata1 = new TaskMetadata();
            taskMetadata1.setMetadata("Metadata 1");

            TaskMetadata taskMetadata2 = new TaskMetadata();
            taskMetadata2.setMetadata("Metadata 2");

            TaskMetadata taskMetadata3 = new TaskMetadata();
            taskMetadata3.setMetadata("Metadata 3");

            task.setTaskMetadata(taskMetadata3);

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
