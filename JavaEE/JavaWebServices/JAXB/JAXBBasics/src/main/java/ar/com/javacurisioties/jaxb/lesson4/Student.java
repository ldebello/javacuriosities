package ar.com.javacurisioties.jaxb.lesson4;


import ar.com.javacurisioties.jaxb.lesson4.adapter.DateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
public class Student {

    private String name;

    @XmlElement(name = "birthdate")
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date birthdate;

    @XmlElement(name = "regular")
    private Boolean regular;

    public Student() {
    }

    public Student(String name, Date birthdate, Boolean regular) {
        this.name = name;
        this.birthdate = birthdate;
        this.regular = regular;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Boolean getRegular() {
        return regular;
    }

    public void setRegular(Boolean regular) {
        this.regular = regular;
    }
}