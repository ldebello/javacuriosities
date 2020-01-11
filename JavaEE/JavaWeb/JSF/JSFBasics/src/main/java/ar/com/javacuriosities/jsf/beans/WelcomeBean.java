package ar.com.javacuriosities.jsf.beans;

import java.io.Serializable;

public class WelcomeBean implements Serializable {

    private String name;
    private String lastName;

    public WelcomeBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
