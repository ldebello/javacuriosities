package ar.com.javacuriosities.hibernate.associations;

import ar.com.javacuriosities.hibernate.model.unidirectional.Address;
import ar.com.javacuriosities.hibernate.model.unidirectional.Client;
import ar.com.javacuriosities.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class UnidirectionalOneToOne {

    public static void main(String[] args) {
        // Obtenemos una Session Factory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        // Abrimos y trabajamos sobre una Session
        try (Session session = sessionFactory.openSession()) {
            Client client1 = new Client();
            client1.setName("Cosme Fulanito");

            Client client2 = new Client();
            client2.setName("Pedro Picapiedra");

            Client client3 = new Client();
            client3.setName("Pablo Marmol");

            Address address = new Address();
            address.setStreet("Lombard");
            address.setNumber(1);

            client1.setAddress(address);

            session.save(address);
            session.save(client1);
            session.save(client2);
            session.save(client3);

            session.getTransaction().commit();
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
