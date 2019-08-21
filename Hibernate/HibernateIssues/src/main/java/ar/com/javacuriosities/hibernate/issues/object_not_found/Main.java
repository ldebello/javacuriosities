package ar.com.javacuriosities.hibernate.issues.object_not_found;


import ar.com.javacuriosities.hibernate.issues.object_not_found.model.Person;
import ar.com.javacuriosities.hibernate.issues.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {

        try {
            // Obtenemos una Session Factory
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            try (Session session = sessionFactory.openSession()) {

                Person personFromDB = session.load(Person.class, 99L);

                if (Math.random() > 0.5) {
                    System.out.println("Name: " + personFromDB.getName());
                } else {
                    System.out.println("You are lucky");
                }
            }
        } finally {
            HibernateUtil.shutdown();
        }
    }

}
