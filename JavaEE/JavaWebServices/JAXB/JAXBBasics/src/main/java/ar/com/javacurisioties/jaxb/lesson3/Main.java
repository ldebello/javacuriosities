package ar.com.javacurisioties.jaxb.lesson3;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        try {
            BookStore bookStore = new BookStore();
            bookStore.setAddress("Calle Sin Numero");
            bookStore.setEmail("luisdebello.cursos@gmail.com");

            // Definimos los libros
            List<Book> books = new ArrayList<>();

            books.add(new Book("Book 1"));
            books.add(new Book("Book 2"));
            books.add(new Book("Book 3"));

            // Definimos los empleados
            List<Employee> employees = new ArrayList<>();

            Employee payEmployee = new PayEmployee("Employee 1", 1000d);
            Employee internshipEmployee = new InternshipEmployee("Internship 1", 6);

            employees.add(payEmployee);
            employees.add(internshipEmployee);

            // Definimos las sucursales
            Map<Integer, String> offices = new HashMap<>();
            offices.put(1, "Office 1");
            offices.put(2, "Office 2");

            bookStore.setBooks(books);
            bookStore.setEmployees(employees);
            bookStore.setOffices(offices);

            JAXBContext jaxbContext = JAXBContext.newInstance(BookStore.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // Pretty print
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(bookStore, System.out);
        } catch (JAXBException e) {
            // Log and Handle exception
            e.printStackTrace();
        }
    }
}
