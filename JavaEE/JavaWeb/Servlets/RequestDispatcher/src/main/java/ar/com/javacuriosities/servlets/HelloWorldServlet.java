package ar.com.javacuriosities.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class HelloWorldServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Establecemos el tipo de contenido
        response.setContentType("text/html;charset=UTF-8");

        // Obtenemos el stream de salida
        PrintWriter out = response.getWriter();

        // Obtenemos el atributo enviado por el otro Servlet
        Date currentDate = (Date) request.getAttribute("currentDate");

        out.println("Hola Mundo (" + currentDate + ")");
    }
}
