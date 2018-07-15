package ar.com.javacuriosities.hibernate.cascade_and_fetch;

import ar.com.javacuriosities.hibernate.model.Address;
import ar.com.javacuriosities.hibernate.model.MobilePhone;
import ar.com.javacuriosities.hibernate.model.Person;
import ar.com.javacuriosities.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class CascadeAndFetch {

    public static void main(String[] args) {
        try {
            // Obtenemos una Session Factory
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            try (Session session = sessionFactory.openSession()) {
                session.beginTransaction();

                Person person = new Person();
                person.setName("Cosme Fulanito");

                Address primaryAddress = new Address();
                primaryAddress.setStreet("Wall Street");
                primaryAddress.setNumber(34);

                Address secondaryAddress = new Address();
                secondaryAddress.setStreet("Lombard");
                secondaryAddress.setNumber(1);

                MobilePhone mobilePhone1 = new MobilePhone();
                mobilePhone1.setNumber("12345678");

                MobilePhone mobilePhone2 = new MobilePhone();
                mobilePhone2.setNumber("98765432");

                MobilePhone mobilePhone3 = new MobilePhone();
                mobilePhone3.setNumber("58961547");

                person.setPrimaryAddress(primaryAddress);
                person.setSecondaryAddress(secondaryAddress);

                List<MobilePhone> mobilePhones = new ArrayList<MobilePhone>();

                mobilePhones.add(mobilePhone1);
                mobilePhones.add(mobilePhone2);
                mobilePhones.add(mobilePhone3);

                session.save(person);

                session.save(mobilePhone1);
                session.save(mobilePhone2);
                session.save(mobilePhone3);

                person.setMobilePhones(mobilePhones);

                session.getTransaction().commit();

            } catch (Exception e) {
                // Log and Handle exception
                e.printStackTrace();
            }

            try (Session session = sessionFactory.openSession()) {
                Person personFromDB = (Person) session.get(Person.class, 1);

                System.out.println("Getting Primary Address");
                System.out.println(personFromDB.getPrimaryAddress());

                System.out.println("Getting Secondary Address ID");
                System.out.println(personFromDB.getSecondaryAddress().getId());

                System.out.println("Getting Secondary Address");
                System.out.println(personFromDB.getSecondaryAddress());

                System.out.println("Iterating Mobile Phone");
                List<MobilePhone> mobilePhoneFromDB = personFromDB.getMobilePhones();

                for (MobilePhone mobilePhone : mobilePhoneFromDB) {
                    System.out.println(mobilePhone);
                }

            } catch (Exception e) {
                // Log and Handle exception
                e.printStackTrace();
            }
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
