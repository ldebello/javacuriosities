package ar.com.javacuriosities.rs.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Client {

    private Integer id;
    private String name;
    private Integer age;

    public Client() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}