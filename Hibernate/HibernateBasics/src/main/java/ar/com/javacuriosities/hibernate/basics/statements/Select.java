package ar.com.javacuriosities.hibernate.basics.statements;


import ar.com.javacuriosities.hibernate.model.Product;
import ar.com.javacuriosities.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class Select {

    public static void main(String[] args) {
        // Obtenemos una Session Factory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        // Abrimos y trabajamos sobre una Session
        try (Session session = sessionFactory.openSession()) {

            // Ejecutamos una consulta HQL (Hibernate Query Language)
            List<Product> products = session.createQuery("FROM Product").list();

            for (Product product : products) {
                System.out.println(product);
            }
        } finally {
            HibernateUtil.shutdown();
        }
    }
}