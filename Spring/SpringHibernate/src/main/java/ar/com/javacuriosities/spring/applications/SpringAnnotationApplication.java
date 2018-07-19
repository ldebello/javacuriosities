package ar.com.javacuriosities.spring.applications;

import ar.com.javacuriosities.hibernate.dao.BookDAO;
import ar.com.javacuriosities.hibernate.model.annotations.Book;
import ar.com.javacuriosities.spring.config.HibernateConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringAnnotationApplication {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);

        BookDAO bookDAO = context.getBean(BookDAO.class);

        Book book = new Book();

        book.setName("Harry Potter");

        bookDAO.saveBook(book);
    }
}
