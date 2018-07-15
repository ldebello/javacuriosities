package ar.com.javacuriosities.hibernate.associations;

import ar.com.javacuriosities.hibernate.model.unidirectional.Client;
import ar.com.javacuriosities.hibernate.model.unidirectional.Company;
import ar.com.javacuriosities.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class UnidirectionalManyToMany {

    public static void main(String[] args) {
        // Obtenemos una Session Factory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        // Abrimos y trabajamos sobre una Session
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Company company1 = new Company();
            company1.setName("Company-1");

            Company company2 = new Company();
            company2.setName("Company-2");

            Client client1 = new Client();
            client1.setName("Cosme Fulanito");

            Client client2 = new Client();
            client2.setName("Pedro Picapiedra");

            Client client3 = new Client();
            client3.setName("Pablo Marmol");

            List<Client> clientsCompany1 = new ArrayList<Client>();
            clientsCompany1.add(client1);
            clientsCompany1.add(client2);
            clientsCompany1.add(client3);

            List<Client> clientsCompany2 = new ArrayList<Client>();
            clientsCompany2.add(client1);

            company1.setClients(clientsCompany1);
            company2.setClients(clientsCompany2);

            session.save(client1);
            session.save(client2);
            session.save(client3);

            session.save(company1);
            session.save(company2);

            session.getTransaction().commit();
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
