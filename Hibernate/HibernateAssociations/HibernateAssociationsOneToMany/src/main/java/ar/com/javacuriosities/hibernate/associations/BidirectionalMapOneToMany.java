package ar.com.javacuriosities.hibernate.associations;

import ar.com.javacuriosities.hibernate.model.bidirectional.Client;
import ar.com.javacuriosities.hibernate.model.bidirectional.MobilePhone;
import ar.com.javacuriosities.hibernate.model.bidirectional_map.Execution;
import ar.com.javacuriosities.hibernate.model.bidirectional_map.Parameters;
import ar.com.javacuriosities.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BidirectionalMapOneToMany {
    public static void main(String[] args) {
        // Obtenemos una Session Factory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        // Abrimos y trabajamos sobre una Session
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Execution execution1 = new Execution();
            execution1.setName("Java-Process");

            Parameters parameter1 = new Parameters();
            parameter1.setName("-Xms");
            parameter1.setValue("256");
            parameter1.setExecution(execution1);

            Parameters parameter2 = new Parameters();
            parameter2.setName("-Xmx");
            parameter2.setValue("512");
            parameter2.setExecution(execution1);

            Map<String, Parameters> parameters = new HashMap<String, Parameters>();

            parameters.put(parameter1.getName(), parameter1);
            parameters.put(parameter2.getName(), parameter2);

            execution1.setParameters(parameters);

            session.save(execution1);
            session.save(parameter1);
            session.save(parameter2);

            session.getTransaction().commit();
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
