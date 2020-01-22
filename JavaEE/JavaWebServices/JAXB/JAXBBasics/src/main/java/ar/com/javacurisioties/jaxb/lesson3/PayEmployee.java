package ar.com.javacurisioties.jaxb.lesson3;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pay")
public class PayEmployee extends Employee {

    @XmlElement
    private Double salary;

    public PayEmployee() {
    }

    public PayEmployee(String name, Double salary) {
        super(name);
        this.salary = salary;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
