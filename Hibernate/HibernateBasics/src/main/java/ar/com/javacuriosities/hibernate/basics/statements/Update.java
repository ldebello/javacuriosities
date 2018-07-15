package ar.com.javacuriosities.hibernate.basics.statements;


import ar.com.javacuriosities.hibernate.model.Product;
import ar.com.javacuriosities.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Update {

    public static void main(String[] args) {
        // Obtenemos una Session Factory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        // Abrimos y trabajamos sobre una Session
        try (Session session = sessionFactory.openSession()) {
            // Instanciamos un producto con el ID a actualizar
            Product product = new Product();
            product.setId(1L);

            // Iniciamos una transacción
            Transaction tx = session.beginTransaction();

            // Modificamos el producto
            session.update(product);

            // Hacemos commit sobre la transacción
            tx.commit();

            System.out.println("Product updated...");
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
