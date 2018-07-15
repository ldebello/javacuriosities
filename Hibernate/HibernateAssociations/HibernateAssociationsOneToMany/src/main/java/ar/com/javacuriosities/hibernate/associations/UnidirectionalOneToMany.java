package ar.com.javacuriosities.hibernate.associations;

import ar.com.javacuriosities.hibernate.model.unidirectional.Department;
import ar.com.javacuriosities.hibernate.model.unidirectional.Employee;
import ar.com.javacuriosities.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class UnidirectionalOneToMany {

    public static void main(String[] args) {
        // Obtenemos una Session Factory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        // Abrimos y trabajamos sobre una Session
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Department department = new Department();
            department.setName("Engineering");

            Employee employee1 = new Employee();
            employee1.setName("Cosme Fulanito");

            Employee employee2 = new Employee();
            employee2.setName("Pablo Marmol");

            List<Employee> employees = new ArrayList<Employee>();

            employees.add(employee1);
            employees.add(employee2);

            department.setEmployees(employees);

            session.save(employee1);
            session.save(employee2);

            session.save(department);

            session.getTransaction().commit();
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
