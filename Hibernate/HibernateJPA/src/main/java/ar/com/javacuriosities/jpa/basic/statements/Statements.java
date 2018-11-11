package ar.com.javacuriosities.jpa.basic.statements;

import ar.com.javacuriosities.jpa.model.Student;
import ar.com.javacuriosities.jpa.utils.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

public class Statements {
    public static void main(String[] args) {
        insert("Cosme Fulanito", 99);
        insert("Pedro Picapiedra", 100);
        insert("Sancho Panzo", 66);

        upate(2L, "Pedro Picapiedra", 25);

        delete(1L);

        List<Student> students = selectAll();

        for (Student student : students) {
            System.out.println(student);
        }

        JPAUtil.shutdown();
    }

    private static void insert(String name, int age) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = null;

        try {
            tx = entityManager.getTransaction();
            tx.begin();

            Student student = new Student();
            student.setName(name);
            student.setAge(age);

            entityManager.persist(student);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            // Log and Handle exception
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    private static void upate(Long id, String name, int age) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = null;

        try {
            tx = entityManager.getTransaction();
            tx.begin();

            Student student = entityManager.find(Student.class, id);

            student.setName(name);
            student.setAge(age);

            entityManager.persist(student);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            // Log and Handle exception
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    private static void delete(Long id) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = null;

        try {
            tx = entityManager.getTransaction();
            tx.begin();

            Student student = entityManager.find(Student.class, id);

            entityManager.remove(student);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            // Log and Handle exception
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    private static List<Student> selectAll() {
        List<Student> students = new ArrayList<>();

        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

        try {
            students = entityManager.createQuery("SELECT s FROM Student s", Student.class).getResultList();
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return students;
    }
}