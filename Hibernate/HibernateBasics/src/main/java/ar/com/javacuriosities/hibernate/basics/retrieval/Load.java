package ar.com.javacuriosities.hibernate.basics.retrieval;

import ar.com.javacuriosities.hibernate.model.Product;
import ar.com.javacuriosities.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * El método load() siempre retorna un Proxy desde la base de datos
 * y recién se consulta la base de datos al momento de consumir sus datos
 */
public class Load {

    public static void main(String[] args) {
        // Obtenemos una Session Factory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        // Abrimos y trabajamos sobre una Session
        try (Session session = sessionFactory.openSession()) {

            Product product = (Product) session.load(Product.class, Long.valueOf(1));

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