package ar.com.javacuriosities.jsf.beans;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("childBean")
@RequestScoped
public class ChildBean {

    private String description;

    public ChildBean() {
        System.out.println("Constructor ChildBean");
    }

    @PostConstruct
    public void initialize() {
        System.out.println("Inicializar ChildBean");
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
