package ar.com.javacuriosities.hibernate.dao;

import ar.com.javacuriosities.hibernate.model.annotations.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class BookDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Book getBook(long id) {
        return getCurrentSession().get(Book.class, id);
    }

    public void saveBook(Book entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    public void updateBook(Book entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    public void deleteBook(long id) {
        Book entity = getBook(id);
        getCurrentSession().delete(entity);
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession() != null ? sessionFactory.getCurrentSession() : sessionFactory.openSession();
    }
}
