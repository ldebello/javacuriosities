package ar.com.javacuriosities.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name="employees")
public class Employee extends Person {

    private String department;

    @Temporal(TemporalType.DATE)
    @Column(name = "firstDate")
    private Date firstDate;

    public Employee() {
    }

    public Employee(String name, String lastName, String department, Date firstDate) {
        super(name, lastName);
        this.department = department;
        this.firstDate = firstDate;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Date getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(Date firstDate) {
        this.firstDate = firstDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "department='" + department + '\'' +
                ", firstDate=" + firstDate +
                '}';
    }
}
