package ar.com.javacuriosities.jsf.converter.model;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("userData")
@SessionScoped
public class UserData implements Serializable {

    private static final long serialVersionUID = 1L;
    private UrlData data;
    private double salary;

    public UrlData getData() {
        return data;
    }

    public void setData(UrlData data) {
        this.data = data;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}