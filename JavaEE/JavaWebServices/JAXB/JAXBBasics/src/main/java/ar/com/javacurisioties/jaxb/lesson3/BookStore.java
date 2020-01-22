package ar.com.javacurisioties.jaxb.lesson3;


import ar.com.javacurisioties.jaxb.lesson3.adapter.MapAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;
import java.util.Map;

@XmlRootElement(name = "bookstore")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"address", "books", "offices", "employees"})
public class BookStore {

    private String address;
    @XmlTransient
    private String email;
    @XmlElementWrapper
    @XmlElement(name = "books")
    private List<Book> books;
    @XmlElementWrapper
    @XmlElementRef
    private List<Employee> employees;
    @XmlJavaTypeAdapter(MapAdapter.class)
    @XmlElement(name = "offices")
    private Map<Integer, String> offices;

    public BookStore() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Map<Integer, String> getOffices() {
        return offices;
    }

    public void setOffices(Map<Integer, String> offices) {
        this.offices = offices;
    }
}
