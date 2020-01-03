package ar.com.javacurisioties.jsp.model;

import java.io.Serializable;

/*
 * Por regla general vamos a hacer que los objetos que son guardados en la session
 * sean serializable. Vamos a aplicar las mismas reglas que los POJOs (Plain Old Java Object)
 * Serializable, Constructor por defecto y Getters/Setters
 */
public class UserData implements Serializable {

    private String name;
    private Integer age;
    private String email;

    public UserData() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
