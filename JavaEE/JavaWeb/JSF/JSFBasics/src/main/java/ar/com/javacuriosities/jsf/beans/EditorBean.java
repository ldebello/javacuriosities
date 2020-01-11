package ar.com.javacuriosities.jsf.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("editor")
@RequestScoped
public class EditorBean {

    private String value = "PrimeFaces";

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void print() {
        System.out.println("****Texto****");
        System.out.println(value);
    }
}