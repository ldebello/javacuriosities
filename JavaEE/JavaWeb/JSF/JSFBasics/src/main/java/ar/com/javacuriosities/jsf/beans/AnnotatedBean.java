package ar.com.javacuriosities.jsf.beans;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.ManagedProperty;
import javax.inject.Named;

@Named("annotatedBeanData")
@RequestScoped
public class AnnotatedBean {

    private String name;

    @ManagedProperty(value = "#{childBean}")
    private ChildBean child;

    public AnnotatedBean() {
        this.name = "Valor por defecto";
        System.out.println("Constructor AnnotatedBean");
    }

    @PostConstruct
    public void initialize() {
        System.out.println("Inicializar AnnotatedBean");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ChildBean getChild() {
        return child;
    }

    public void setChild(ChildBean child) {
        this.child = child;
    }
}
