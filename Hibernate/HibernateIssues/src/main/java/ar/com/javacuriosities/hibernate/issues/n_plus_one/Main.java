package ar.com.javacuriosities.hibernate.issues.n_plus_one;


import ar.com.javacuriosities.hibernate.issues.n_plus_one.model.Building;
import ar.com.javacuriosities.hibernate.issues.n_plus_one.model.Company;
import ar.com.javacuriosities.hibernate.issues.n_plus_one.model.Corporation;
import ar.com.javacuriosities.hibernate.issues.n_plus_one.model.CorporationOwner;
import ar.com.javacuriosities.hibernate.issues.n_plus_one.model.Owner;
import ar.com.javacuriosities.hibernate.issues.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Obtenemos una Session Factory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        // Abrimos y trabajamos sobre una Session
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            for (int i = 0; i < 2; i++) {
                Corporation corporation = new Corporation();
                corporation.setName("Corporation-" + i);

                CorporationOwner corporationOwner1 = new CorporationOwner();
                corporationOwner1.setName("First Corporation Owner");
                corporationOwner1.setCorporation(corporation);

                CorporationOwner corporationOwner2 = new CorporationOwner();
                corporationOwner2.setName("Second Corporation Owner");
                corporationOwner2.setCorporation(corporation);

                session.saveOrUpdate(corporationOwner1);
                session.saveOrUpdate(corporationOwner2);
                for (int j = 0; j < 4; j++) {
                    Company company = new Company();
                    company.setName("Company-" + j);

                    session.saveOrUpdate(company);

                    for (int k = 0; k < 10; k++) {
                        Owner owner = new Owner();
                        owner.setName("Building-" + k + "/" + "Owner-" + k);

                        session.saveOrUpdate(owner);

                        Building building = new Building();

                        building.setOwner(owner);
                        building.setCompany(company);
                        building.setName("Building-" + k);

                        session.saveOrUpdate(building);
                    }

                    corporation.addCompany(company);
                }

                session.saveOrUpdate(corporation);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        }

        try {
            nPlusOneFromOneToMany(sessionFactory);
            nPlusOneFromOneToManyAndMappedBy(sessionFactory);
            nPlusOneFromManyToOneEager(sessionFactory);
            nPlusOneFromManyToOneLazy(sessionFactory);
            nPlusOneJoinFetch(sessionFactory);
        } finally {
            HibernateUtil.shutdown();
        }
    }

    public static void nPlusOneFromOneToMany(SessionFactory sessionFactory) {
        System.out.println("Start");
        try (Session session = sessionFactory.openSession()) {
            Query<Corporation> query = session.createQuery("FROM Corporation", Corporation.class);

            List<Corporation> corporations = query.list();

            System.out.println("Iteration");
            for (Corporation corporation : corporations) {
                System.out.println("Corporation: " + corporation.getName());
                System.out.println("Companies: " + corporation.getCompanies().size());

            }

        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        }
        System.out.println("Finish");
    }

    public static void nPlusOneFromOneToManyAndMappedBy(SessionFactory sessionFactory) {
        System.out.println("Start");
        try (Session session = sessionFactory.openSession()) {
            Query<Corporation> query = session.createQuery("FROM Corporation", Corporation.class);

            List<Corporation> corporations = query.list();

            System.out.println("Iteration");
            for (Corporation corporation : corporations) {
                System.out.println("Corporation: " + corporation.getName());
                System.out.println("Corporation Owners: " + corporation.getCorporationOwners().size());
            }

        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        }
        System.out.println("Finish");
    }

    public static void nPlusOneFromManyToOneEager(SessionFactory sessionFactory) {
        System.out.println("Start");
        try (Session session = sessionFactory.openSession()) {
            Query<Building> query = session.createQuery("FROM Building", Building.class);

            List<Building> buildings = query.list();

            System.out.println("Iteration");
            for (Building building : buildings) {
                System.out.println("Building: " + building.getName());
                System.out.println("Company: " + building.getCompany().getName());
            }

        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        }

        System.out.println("Finish");
    }

    public static void nPlusOneFromManyToOneLazy(SessionFactory sessionFactory) {
        System.out.println("Start");

        try (Session session = sessionFactory.openSession()) {
            Query<Building> query = session.createQuery("FROM Building", Building.class);

            List<Building> buildings = query.list();

            System.out.println("Iteration");
            for (Building building : buildings) {
                System.out.println("Building: " + building.getName());
                System.out.println("Owner: " + building.getOwner().getName());
            }

        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        }

        System.out.println("Finish");
    }

    public static void nPlusOneJoinFetch(SessionFactory sessionFactory) {
        System.out.println("Start");

        try (Session session = sessionFactory.openSession()) {
            Query<Building> query = session.createQuery("FROM Building b JOIN FETCH b.company", Building.class);

            List<Building> buildings = query.list();

            System.out.println("Third Iteration");
            for (Building building : buildings) {
                System.out.println("Building: " + building.getName());
                System.out.println("Company: " + building.getCompany().getName());
            }

        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        }

        System.out.println("Finish");
    }
}
