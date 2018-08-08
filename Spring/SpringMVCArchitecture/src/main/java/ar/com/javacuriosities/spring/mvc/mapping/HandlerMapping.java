package ar.com.javacuriosities.spring.mvc.mapping;

import ar.com.javacuriosities.spring.app.controllers.EmployeeController;
import ar.com.javacuriosities.spring.app.controllers.LoginController;
import ar.com.javacuriosities.spring.mvc.controller.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

public class HandlerMapping {

    private static final Map<String, AbstractController> controllers = new HashMap<String, AbstractController>();

    static {
        controllers.put("/login.do", new LoginController());
        controllers.put("/employees.do", new EmployeeController());
    }
    public static AbstractController getController(HttpServletRequest request, HttpServletResponse response) {
        return controllers.get(request.getServletPath());
    }
}