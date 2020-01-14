package ar.com.javacuriosities.jsf.lifecycle;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class HelloBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name = "";

    public HelloBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String saludar() {
        return !"".equals(name) ? "Ajax message : Welcome " + name : "";
    }
}
