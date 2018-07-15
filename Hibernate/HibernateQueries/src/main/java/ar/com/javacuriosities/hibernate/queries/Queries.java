package ar.com.javacuriosities.hibernate.queries;

import ar.com.javacuriosities.hibernate.model.Address;
import ar.com.javacuriosities.hibernate.model.Brand;
import ar.com.javacuriosities.hibernate.model.Product;
import ar.com.javacuriosities.hibernate.utils.HibernateUtil;
import ar.com.javacuriosities.hibernate.utils.QueriesUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Queries {

    public static void main(String[] args) {
        // Obtenemos una Session Factory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try {
            // Abrimos y trabajamos sobre una Session
            try (Session session = sessionFactory.openSession()) {

                // Instanciamos las marcas
                Brand brand1 = new Brand();
                brand1.setName("Brand 1");

                Brand brand2 = new Brand();
                brand2.setName("Brand 2");

                Brand brand3 = new Brand();
                brand3.setName("Brand 3");

                // Instanciamos los domicilios
                Address address1 = new Address();
                address1.setStreet("Lombard");
                address1.setNumber(1);

                Address address2 = new Address();
                address2.setStreet("Wall Street");
                address2.setNumber(34);

                // Instanciamos los productos
                Product product1 = new Product("Apple", 11.5, new Date());
                Product product2 = new Product("Lemon", 15.6, new Date());
                Product product3 = new Product("Mandarine   ", 13.2, new Date());

                List<Product> productsBrand1 = new ArrayList<Product>();

                productsBrand1.add(product1);

                brand1.setAddress(address1);
                brand1.setProducts(productsBrand1);

                List<Product> productsBrand2 = new ArrayList<Product>();

                productsBrand2.add(product2);
                productsBrand2.add(product3);

                brand2.setAddress(address2);
                brand2.setProducts(productsBrand2);

                // Iniciamos una transacción
                Transaction tx = session.beginTransaction();

                // Salvamos las marcas
                session.save(brand1);
                session.save(brand2);
                session.save(brand3);

                // Hacemos commit sobre la transacción
                tx.commit();
            } catch (Exception e) {
                // Log and Handle exception
                e.printStackTrace();
            }

            System.out.println("Data ready to be query...");

            QueriesUtils.nativeQuery();
            QueriesUtils.nativeQueryWithScalarValues();
            QueriesUtils.nativeQueryWithEntities();

            QueriesUtils.hqlDMLQueries();
            QueriesUtils.hqlQueries();

            QueriesUtils.criteriaHibernateAPIQueries();
            QueriesUtils.criteriaJPAAPIQueries();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
