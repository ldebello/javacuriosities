package ar.com.javacuriosities.hibernate.associations;

import ar.com.javacuriosities.hibernate.model.bidirectional.Employee;
import ar.com.javacuriosities.hibernate.model.bidirectional.Project;
import ar.com.javacuriosities.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class BidirectionalManyToMany {

    public static void main(String[] args) {
        // Obtenemos una Session Factory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        // Abrimos y trabajamos sobre una Session
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Project project1 = new Project();
            project1.setName("Project-1");

            Project project2 = new Project();
            project2.setName("Project-2");

            Employee employee1 = new Employee();
            employee1.setName("Cosme Fulanito");

            Employee employee2 = new Employee();
            employee2.setName("Pedro Picapiedra");

            Employee employee3 = new Employee();
            employee3.setName("Pablo Marmol");

            List<Employee> employeesCompany1 = new ArrayList<Employee>();
            employeesCompany1.add(employee1);
            employeesCompany1.add(employee2);
            employeesCompany1.add(employee3);

            List<Employee> employeesCompany2 = new ArrayList<Employee>();
            employeesCompany2.add(employee1);

            project1.setEmployees(employeesCompany1);
            project2.setEmployees(employeesCompany2);

            session.save(employee1);
            session.save(employee2);
            session.save(employee3);

            session.save(project1);
            session.save(project2);

            session.getTransaction().commit();
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
