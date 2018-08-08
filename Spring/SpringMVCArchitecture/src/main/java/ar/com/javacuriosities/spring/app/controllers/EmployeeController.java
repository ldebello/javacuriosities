package ar.com.javacuriosities.spring.app.controllers;

import ar.com.javacuriosities.spring.app.model.Employee;
import ar.com.javacuriosities.spring.mvc.controller.AbstractController;
import ar.com.javacuriosities.spring.mvc.model.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class EmployeeController extends AbstractController {

    @Override
    protected ModelAndView handleRenderRequestInternal(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("employees");

        List<Employee> employees = new ArrayList<Employee>();

        Employee employee = new Employee();
        employee.setName("Cosme Fulanito");

        employee.setAge(Integer.valueOf(27));

        employees.add(employee);

        employee = new Employee();
        employee.setName("Pedro Picapiedra");

        employee.setAge(Integer.valueOf(28));

        employees.add(employee);

        employee = new Employee();
        employee.setName("Pablo Marmol");
        employee.setAge(Integer.valueOf(29));

        employees.add(employee);

        modelAndView.addObject("employees", employees);

        return modelAndView;
    }
}
