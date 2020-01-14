package ar.com.javacuriosities.jsf.beans;

public class EmployeeBean {

    private String name;
    private String lastName;
    private String dni;
    private Double salary;
    private EmployeeCollectionBean employeeCollection;

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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public void setEmployeeCollection(EmployeeCollectionBean employeeCollection) {
        this.employeeCollection = employeeCollection;
    }

    public EmployeeCollectionBean getEmployeeCollection() {
        return employeeCollection;
    }

    public String add() {
        this.getEmployeeCollection().addEmployee(this.name, this.lastName, this.dni, this.salary);
        return "add";
    }

    public String remove() {
        this.getEmployeeCollection().removeEmployee(this.dni);
        this.clearForm();
        return "remove";
    }

    private void clearForm() {
        this.dni = null;
        this.name = null;
        this.lastName = null;
        this.salary = null;
    }
}
