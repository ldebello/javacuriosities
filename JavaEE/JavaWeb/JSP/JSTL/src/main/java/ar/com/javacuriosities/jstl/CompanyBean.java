package ar.com.javacuriosities.jstl;

import java.util.ArrayList;
import java.util.List;

public class CompanyBean {

    public CompanyBean() {
    }

    public List<Employee> getEmpleados() {
        List<Employee> employees = new ArrayList();
        employees.add(new Employee("Juan", "Perez", "5.845.269"));
        employees.add(new Employee("Ernesto", "Martin", "2.598.229"));
        employees.add(new Employee("Mauro", "Reyes", "6.248.962"));
        employees.add(new Employee("Cecilia", "Perez", "2.585.213"));
        employees.add(new Employee("Maria", "Polis", "6.854.285"));
        return employees;
    }
}
