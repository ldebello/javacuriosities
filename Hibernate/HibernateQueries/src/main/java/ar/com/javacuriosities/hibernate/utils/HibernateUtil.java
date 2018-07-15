package ar.com.javacuriosities.hibernate.utils;


import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public final class HibernateUtil {

    private HibernateUtil() {
    }

    public static SessionFactory getSessionFactory() {
        return SessionFactoryHolder.SESSION_FACTORY;
    }

    public static void shutdown() {
        StandardServiceRegistryBuilder.destroy(ServiceRegistryHolder.REGISTRY);
    }

    private static class ServiceRegistryHolder {
        private static ServiceRegistry REGISTRY = buildServiceRegistry();

        private static ServiceRegistry buildServiceRegistry() {
            return new StandardServiceRegistryBuilder()
                    .configure()
                    .build();
        }
    }

    private static class SessionFactoryHolder {
        private static SessionFactory SESSION_FACTORY = buildSessionFactory();

        private static SessionFactory buildSessionFactory() {
            MetadataSources sources = new MetadataSources(ServiceRegistryHolder.REGISTRY);
            Metadata metadata = sources.getMetadataBuilder().build();
            return metadata.getSessionFactoryBuilder().build();
        }
    }
}