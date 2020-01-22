package ar.com.javacurisioties.jaxb.lesson3;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "internship")
public class InternshipEmployee extends Employee {

    @XmlElement
    private Integer hours;

    public InternshipEmployee() {
    }

    public InternshipEmployee(String name, Integer hours) {
        super(name);
        this.hours = hours;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }
}
