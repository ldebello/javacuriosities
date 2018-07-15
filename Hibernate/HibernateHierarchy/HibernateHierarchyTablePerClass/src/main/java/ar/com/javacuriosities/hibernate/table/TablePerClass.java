package ar.com.javacuriosities.hibernate.table;

import ar.com.javacuriosities.hibernate.model.Boss;
import ar.com.javacuriosities.hibernate.model.Employee;
import ar.com.javacuriosities.hibernate.model.Person;
import ar.com.javacuriosities.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class TablePerClass {

    public static void main(String[] args) {
        // Obtenemos una Session Factory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Person person = new Person("Cosme", "Fulanito");
            session.save(person);

            Employee employee = new Employee("Pablo", "Marmol", "Building", new Date());
            session.save(employee);

            Boss boss = new Boss("Sancho", "Panza", "12345678", 1225);
            session.save(boss);

            session.getTransaction().commit();
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        }

        try (Session session = sessionFactory.openSession()) {
            System.out.println("***** People ***** ");
            Query query = session.createQuery("FROM Person");
            List<Person> people = query.list();

            for (Person currentPerson : people) {
                System.out.println(currentPerson);
            }

            System.out.println("***** Employees ***** ");
            query = session.createQuery("FROM Employee");
            people = query.list();
            for (Person currentEmployee : people) {
                System.out.println(currentEmployee);
            }

            System.out.println("***** Bosses ***** ");
            query = session.createQuery("FROM Boss");
            people = query.list();
            for (Person currentBoss : people) {
                System.out.println(currentBoss);
            }
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
