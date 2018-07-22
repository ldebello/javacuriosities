package ar.com.javacuriosities.spring.applications;

import ar.com.javacuriosities.hibernate.dao.BookDAO;
import ar.com.javacuriosities.hibernate.model.annotations.Book;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringXMLApplication {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application_context.xml");

        BookDAO bookDAO = context.getBean(BookDAO.class);

        Book book = new Book();

        book.setName("Game of Thrones");

        bookDAO.saveBook(book);

    }
}
