package ar.com.javacuriosities.hibernate.issues.lazy_initialization;


import ar.com.javacuriosities.hibernate.issues.lazy_initialization.model.Course;
import ar.com.javacuriosities.hibernate.issues.lazy_initialization.model.Student;
import ar.com.javacuriosities.hibernate.issues.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        try {
            // Obtenemos una Session Factory
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            Course course = new Course();

            // Abrimos y trabajamos sobre una Session
            try (Session session = sessionFactory.openSession()) {
                session.beginTransaction();


                course.setName("Java Hibernate");

                List<Student> students = new ArrayList<>();

                Student student1 = new Student();
                student1.setName("Cosme Fulanito");
                students.add(student1);

                Student student2 = new Student();
                student2.setName("Sancho Panza");
                students.add(student2);

                Student student3 = new Student();
                student3.setName("Pedro Picapiedra");
                students.add(student3);

                course.setStudents(students);

                session.saveOrUpdate(course);

                session.getTransaction().commit();
            }

            try (Session session = sessionFactory.openSession()) {

                Course courseFromDB1 = session.get(Course.class, course.getId());

                System.out.println("1- Students: " + courseFromDB1.getStudents().size());
            }

            Course courseFromDB2 = null;

            try (Session session = sessionFactory.openSession()) {

                courseFromDB2 = session.get(Course.class, course.getId());
            }

            System.out.println("2- Students: " + courseFromDB2.getStudents().size());
        } finally {
            HibernateUtil.shutdown();
        }
    }

}
