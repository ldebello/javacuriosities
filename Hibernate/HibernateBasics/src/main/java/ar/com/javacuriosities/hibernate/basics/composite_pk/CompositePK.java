package ar.com.javacuriosities.hibernate.basics.composite_pk;

import ar.com.javacuriosities.hibernate.model.Invoice;
import ar.com.javacuriosities.hibernate.model.InvoicePK;
import ar.com.javacuriosities.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Date;

public class CompositePK {

    public static void main(String[] args) {
        // Obtenemos una Session Factory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        // Abrimos y trabajamos sobre una Session
        try (Session session = sessionFactory.openSession()) {
            Invoice invoice = new Invoice();
            InvoicePK compositePK = new InvoicePK();

            compositePK.setDate(new Date());
            compositePK.setNumber(1);

            invoice.setClient("Cosme Fulanito");
            invoice.setPrice(100.00);
            invoice.setId(compositePK);

            // Iniciamos una transacción
            Transaction tx = session.beginTransaction();

            session.save(invoice);

            // Hacemos commit sobre la transacción
            tx.commit();
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
