package ar.com.javacuriosities.hibernate.associations;

import ar.com.javacuriosities.hibernate.model.bidirectional.Client;
import ar.com.javacuriosities.hibernate.model.bidirectional.MobilePhone;
import ar.com.javacuriosities.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.HashSet;
import java.util.Set;

public class BidirectionalOneToMany {
    public static void main(String[] args) {
        // Obtenemos una Session Factory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        // Abrimos y trabajamos sobre una Session
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Client client = new Client();
            client.setName("Cosme Fulanito");

            MobilePhone mobilePhone1 = new MobilePhone();
            mobilePhone1.setNumber("12345678");
            mobilePhone1.setClient(client);

            MobilePhone mobilePhone2 = new MobilePhone();
            mobilePhone2.setNumber("98765432");
            mobilePhone2.setClient(client);

            Set<MobilePhone> mobilePhones = new HashSet<MobilePhone>();

            mobilePhones.add(mobilePhone1);
            mobilePhones.add(mobilePhone2);

            client.setMobilePhones(mobilePhones);

            session.save(mobilePhone1);
            session.save(mobilePhone2);
            session.save(client);

            session.getTransaction().commit();
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
