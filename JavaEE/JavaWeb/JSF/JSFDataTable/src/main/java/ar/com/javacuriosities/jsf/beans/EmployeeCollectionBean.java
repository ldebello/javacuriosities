package ar.com.javacuriosities.jsf.beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EmployeeCollectionBean {

    private List<Employee> employees;

    public EmployeeCollectionBean() {
        this.employees = new ArrayList<>();
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void addEmployee(String name, String lastName, String dni, Double salary) {
        this.employees.add(new Employee(name, lastName, dni, salary));
    }

    public void removeEmployee(String dni) {
        for (Iterator<Employee> it = employees.iterator(); it.hasNext(); ) {
            Employee employee = it.next();
            if (dni.equalsIgnoreCase(employee.getDni())) {
                it.remove();
                return;
            }
        }
    }

    public static class Employee {

        private String name;
        private String lastName;
        private String dni;
        private Double salary;

        private Employee(String name, String lastName, String dni, Double salary) {
            this.name = name;
            this.lastName = lastName;
            this.dni = dni;
            this.salary = salary;
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
    }
}
