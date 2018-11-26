package ar.com.javacuriosities.hibernate.cascade_and_fetch;

import ar.com.javacuriosities.hibernate.model.Person;
import ar.com.javacuriosities.hibernate.model.Task;
import ar.com.javacuriosities.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class OrphanRemoval {

    public static void main(String[] args) {
        try {
            // Obtenemos una Session Factory
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            try (Session session = sessionFactory.openSession()) {
                session.beginTransaction();

                Person person = new Person();
                person.setName("Cosme Fulanito");

                Task task1 = new Task();
                task1.setDescription("First Task");

                Task task2 = new Task();
                task2.setDescription("Second Task");

                Task task3 = new Task();
                task3.setDescription("Third Task");

                List<Task> tasks = new ArrayList<>();

                tasks.add(task1);
                tasks.add(task2);
                tasks.add(task3);

                person.setTasks(tasks);

                session.save(person);

                session.getTransaction().commit();
            } catch (Exception e) {
                // Log and Handle exception
                e.printStackTrace();
            }

            try (Session session = sessionFactory.openSession()) {
                Person personFromDB = (Person) session.get(Person.class, 1L);

                session.beginTransaction();

                List<Task> tasks = personFromDB.getTasks();
                Task firstTask = tasks.get(0);

                tasks.remove(firstTask);

                session.getTransaction().commit();

            } catch (Exception e) {
                // Log and Handle exception
                e.printStackTrace();
            }
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
