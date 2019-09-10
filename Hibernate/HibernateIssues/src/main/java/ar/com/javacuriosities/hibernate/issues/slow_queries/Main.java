package ar.com.javacuriosities.hibernate.issues.slow_queries;

import ar.com.javacuriosities.hibernate.issues.slow_queries.model.Server;
import ar.com.javacuriosities.hibernate.issues.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        // Obtenemos una Session Factory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Server server = new Server();

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            server.setName("Server-1");
            server.setDesiredState("STARTED");

            session.saveOrUpdate(server);

            session.getTransaction().commit();
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        }

        Thread t1 = new Thread(new BadNotification(server.getId()));
        Thread t2 = new Thread(new GoodNotification(server.getId()));

        t1.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            // Log and Handle exception
            e.printStackTrace();
        }

        t2.start();
    }

    private static class BadNotification implements Runnable {

        private final Long serverId;

        public BadNotification(Long serverId) {
            this.serverId = serverId;
        }

        @Override
        public void run() {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            try (Session session = sessionFactory.openSession()) {
                session.beginTransaction();

                String hqlUpdate = "UPDATE Server SET state = :serverState WHERE id = :serverId";

                session.createQuery(hqlUpdate)
                        .setParameter("serverState", "APPLYING")
                        .setParameter("serverId", serverId)
                        .executeUpdate();

                TimeUnit.SECONDS.sleep(10);

                session.getTransaction().commit();
            } catch (Exception e) {
                // Log and Handle exception
                e.printStackTrace();
            }
        }
    }

    private static class GoodNotification implements Runnable {

        private final Long serverId;

        public GoodNotification(Long serverId) {
            this.serverId = serverId;
        }

        @Override
        public void run() {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            try (Session session = sessionFactory.openSession()) {
                long start = System.currentTimeMillis();

                session.beginTransaction();

                Server server = session.get(Server.class, serverId);

                server.setState("STARTED");

                session.getTransaction().commit();

                System.out.println("Time: " + TimeUnit.SECONDS.convert(System.currentTimeMillis() - start, TimeUnit.MILLISECONDS));
            } catch (Exception e) {
                // Log and Handle exception
                e.printStackTrace();
            }
        }
    }
}
