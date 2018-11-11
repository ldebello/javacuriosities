package ar.com.javacuriosities.jpa.basic.entity_graph;

import ar.com.javacuriosities.jpa.model.User;
import ar.com.javacuriosities.jpa.utils.JPAUtil;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUtil;
import java.util.HashMap;
import java.util.Map;

public class EntityGraphMain {

    public static void main(String[] args) {
        persistEntity();
        findEntity();
        findEntityWithFetchEntityGraph();
        findEntityWithLoadEntityGraph();
    }

    public static void persistEntity() {
        User user = new User();
        user.setName("Cosme Fulanito");
        user.addPhone("1111-1111", "Cell");
        user.addPhone("2222-2222", "Work");
        user.addAddress("Street", "City", "Country");

        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.persist(user);

        entityManager.getTransaction().commit();

        entityManager.close();
    }

    private static void findEntity() {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

        User user = entityManager.find(User.class, 1L);

        entityManager.close();

        printInitializeStatus(user);
    }

    private static void findEntityWithFetchEntityGraph() {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

        EntityGraph<?> graph = entityManager.getEntityGraph("user-phones-entity-graph");
        Map<String, Object> properties = new HashMap<>();

        properties.put("javax.persistence.fetchgraph", graph);
        User user = entityManager.find(User.class, 1L, properties);

        entityManager.close();

        printInitializeStatus(user);
    }

    private static void findEntityWithLoadEntityGraph() {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

        EntityGraph<?> graph = entityManager.getEntityGraph("user-phones-entity-graph");
        Map<String, Object> properties = new HashMap<>();

        properties.put("javax.persistence.loadgraph", graph);
        User user = entityManager.find(User.class, 1L, properties);

        entityManager.close();

        printInitializeStatus(user);
    }

    private static void printInitializeStatus(User user) {
        PersistenceUtil persistenceUtil = JPAUtil.getEntityManagerFactory().getPersistenceUnitUtil();
        System.out.println("          User loaded: " + persistenceUtil.isLoaded(user));
        System.out.println("     User.name loaded: " + persistenceUtil.isLoaded(user, "name"));
        System.out.println("User.addresses loaded: " + persistenceUtil.isLoaded(user, "addresses"));
        System.out.println("   User.phones loaded: " + persistenceUtil.isLoaded(user, "phones"));
    }
}
