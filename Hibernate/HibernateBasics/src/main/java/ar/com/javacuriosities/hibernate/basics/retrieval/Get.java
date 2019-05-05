package ar.com.javacuriosities.hibernate.basics.retrieval;

import ar.com.javacuriosities.hibernate.model.Product;
import ar.com.javacuriosities.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/*
 * El método get() retorna NULL cuando el objeto no existe en la DB,
 * si el objeto existe retorna el objeto desde la base sin ningún proxy
 */
public class Get {

    public static void main(String[] args) {
        // Obtenemos una Session Factory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        // Abrimos y trabajamos sobre una Session
        try (Session session = sessionFactory.openSession()) {

            Product product = (Product) session.get(Product.class, 1L);

            System.out.println(product.getClass().getName());
            System.out.println(product.getDescription());
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}