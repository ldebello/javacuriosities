package ar.com.javacuriosities.jsp.el.servlet;

import ar.com.javacuriosities.jsp.el.model.Address;
import ar.com.javacuriosities.jsp.el.model.Employee;
import ar.com.javacuriosities.jsp.el.model.Person;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;

public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Creamos algunos atributos        
        Person person = new Employee();
        person.setName("Pedro");

        request.setAttribute("person", person);

        Employee employee = new Employee();
        Address address = new Address();
        address.setStreet("CalleSinNumero");

        employee.setId(1);
        employee.setName("Cosme Fulanito");
        employee.setAddress(address);

        HttpSession session = request.getSession();
        session.setAttribute("employee", employee);

        String cookieValue = "Tomcat Value";
        String cookieEncoded = URLEncoder.encode(cookieValue, "UTF-8");
        response.addCookie(new Cookie("Usuario.MiCookie", cookieEncoded));
        getServletContext().setAttribute("Usuario.CookieApp", "Tomcat User");

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
        rd.forward(request, response);
    }
}
