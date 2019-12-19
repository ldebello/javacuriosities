package ar.com.javacuriosities.hibernate.transactions;

import ar.com.javacuriosities.hibernate.model.Person;
import ar.com.javacuriosities.hibernate.utils.HibernateUtil;
import ar.com.javacuriosities.hibernate.utils.SavepointContainer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

public class Main {
    public static void main(String[] args) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        try {
            try (Session session = sf.openSession()) {
                session.beginTransaction();

                Person p1 = new Person();
                Person p2 = new Person();
                Person p3 = new Person();

                p1.setName("Cosme Fulanito");
                p2.setName("Sancho Panza");
                p3.setName("Pedro Picapiedra");

                session.save(p1);
                session.save(p2);
                session.save(p3);

                session.getTransaction().commit();
            }

            String savepointName = "FirstSave";
            SavepointContainer savepointContainer = new SavepointContainer();

            try (Session session = sf.openSession()) {
                session.beginTransaction();

                Person p1 = new Person();
                Person p2 = new Person();
                Person p3 = new Person();

                p1.setName("Pablo Marmol");
                p2.setName("Don Quijote");
                p3.setName("Mario Bros");

                session.save(p1);
                session.flush();

                session.doWork(connection -> {
                    Savepoint savepoint1 = connection.setSavepoint(savepointName);
                    savepointContainer.setSavepoint(savepoint1);
                });

                session.save(p2);
                session.flush();

                session.doWork(connection -> {
                     connection.rollback(savepointContainer.getSavepoint());
                });

                session.save(p3);
                session.flush();

                session.getTransaction().commit();
            }
        } finally {
            sf.close();
        }
    }
}
