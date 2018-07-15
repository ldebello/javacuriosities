package ar.com.javacuriosities.hibernate.basics.statements;

import ar.com.javacuriosities.hibernate.model.Product;
import ar.com.javacuriosities.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.IOException;

public class Insert {

    public static void main(String[] args) throws IOException {
        // Obtenemos una Session Factory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        // Abrimos y trabajamos sobre una Session
        try (Session session = sessionFactory.openSession()) {

            // Instanciamos los productos
            Product firstProduct = new Product("Apple", 11.5);
            Product secondProduct = new Product("Lemon", 15.6);
            Product thirdProduct = new Product("Mandarine   ", 13.2);

            // Iniciamos una transacción
            Transaction tx = session.beginTransaction();

            // Salvamos los productos
            session.save(firstProduct);
            session.save(secondProduct);
            session.save(thirdProduct);

            // Hacemos commit sobre la transacción
            tx.commit();

            System.out.println("Products inserted...");
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}