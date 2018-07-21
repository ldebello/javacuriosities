package ar.com.javacuriosities.jpa.utils;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class JPAUtil {

    private JPAUtil() {
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return EntityManagerHolder.ENTITY_MANAGER_FACTORY;
    }

    public static void shutdown() {
        getEntityManagerFactory().close();
    }

    private static class EntityManagerHolder {
        private static EntityManagerFactory ENTITY_MANAGER_FACTORY = buildEntityManagerFactory();

        private static EntityManagerFactory buildEntityManagerFactory() {
            return Persistence.createEntityManagerFactory("DefaultPersistenceContext");
        }
    }
}