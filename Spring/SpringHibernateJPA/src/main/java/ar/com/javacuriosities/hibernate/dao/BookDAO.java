package ar.com.javacuriosities.hibernate.dao;

import ar.com.javacuriosities.hibernate.model.annotations.Book;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Transactional
public class BookDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Book getBook(long id) {
        return entityManager.find(Book.class, id);
    }

    public void saveBook(Book entity) {
        entityManager.persist(entity);
    }

    public void updateBook(Book entity) {
        entityManager.persist(entity);
    }

    public void deleteBook(long id) {
        Book entity = getBook(id);
        entityManager.remove(entity);
    }
}
