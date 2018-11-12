package ar.com.javacuriosities.hibernate.basics.listeners;

import ar.com.javacuriosities.hibernate.model.Address;
import ar.com.javacuriosities.hibernate.model.Person;
import ar.com.javacuriosities.hibernate.model.Product;
import ar.com.javacuriosities.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.List;

public class AnnotationsMain {

    public static void main(String[] args) throws IOException {
        // Obtenemos una Session Factory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        // Abrimos y trabajamos sobre una Session
        try (Session session = sessionFactory.openSession()) {

            // Instanciamos los productos
            Product product = new Product("Apple", 11.5);

            // Iniciamos una transacción
            Transaction tx = session.beginTransaction();
            try {
                // Salvamos los productos
                session.save(product);

                product.setPrice(20.6);

                // Hacemos commit sobre la transacción
                tx.commit();
            } catch (Exception e) {
                // Log and Handle exception
                e.printStackTrace();

                tx.rollback();
            }
        } finally {
            HibernateUtil.shutdown();
        }
    }

}
