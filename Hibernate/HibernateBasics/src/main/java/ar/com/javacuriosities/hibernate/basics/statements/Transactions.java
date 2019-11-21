package ar.com.javacuriosities.hibernate.basics.statements;

import ar.com.javacuriosities.hibernate.model.Product;
import ar.com.javacuriosities.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Transactions {

    public static void main(String[] args) {
        // Obtenemos una Session Factory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        // Abrimos y trabajamos sobre una Session
        try (Session session = sessionFactory.openSession()) {

            // Instanciamos el producto
            Product product = new Product("Apple", 20.0);

            try {
                // Salvamos el producto
                session.save(product);

                System.out.println("Product inserted...");
            } catch (Exception e) {
                // Log and Handle exception
                e.printStackTrace();
            }
        }

        // Abrimos y trabajamos sobre una Session
        try (Session session = sessionFactory.openSession()) {

            // Obtenemos el producto de la DB
            Product product = session.get(Product.class, 1L);

            product.setPrice(25.0);

            try {
                // Salvamos el producto
                session.update(product);

                System.out.println("Product updated...");
            } catch (Exception e) {
                // Log and Handle exception
                e.printStackTrace();
            }
        } finally {
            HibernateUtil.shutdown();
        }
    }
}