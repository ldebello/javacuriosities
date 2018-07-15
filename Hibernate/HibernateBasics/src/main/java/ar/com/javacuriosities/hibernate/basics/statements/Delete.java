package ar.com.javacuriosities.hibernate.basics.statements;

import ar.com.javacuriosities.hibernate.model.Product;
import ar.com.javacuriosities.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Delete {

    public static void main(String[] args) {
        // Obtenemos una Session Factory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        // Abrimos y trabajamos sobre una Session
        try (Session session = sessionFactory.openSession()) {

            // Establecemos el id del producto a eliminar
            Long productId = 1L;

            // Instanciamos un producto con el ID a borrar
            Product product = new Product();
            product.setId(productId);

            // Iniciamos una transacción
            Transaction tx = session.beginTransaction();

            // Eliminamos el producto
            session.delete(product);

            // Hacemos commit sobre la transacción
            tx.commit();

            System.out.println("Product deleted...");
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}