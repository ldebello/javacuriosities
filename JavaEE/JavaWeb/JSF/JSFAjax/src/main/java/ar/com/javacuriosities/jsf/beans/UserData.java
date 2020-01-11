package ar.com.javacuriosities.jsf.beans;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("userData")
@SessionScoped
public class UserData implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String welcomeMessage() {
        return "Hola " + name;
    }
}
