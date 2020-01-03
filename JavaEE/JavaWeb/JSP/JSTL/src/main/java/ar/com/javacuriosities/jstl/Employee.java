package ar.com.javacuriosities.jstl;

public class Employee {

    // Atributos
    private String name = "";
    private String lastName = "";
    private String data = "";

    public Employee() {
    }

    public Employee(String name, String lastName, String data) {
        this.name = name;
        this.lastName = lastName;
        this.data = data;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}