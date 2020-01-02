package ar.com.javacuriosities.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class InitialServlet extends HttpServlet {

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
        // Establecemos el tipo de contenido
        response.setContentType("text/html;charset=UTF-8");

        // Obtenemos el stream de salida
        PrintWriter out = response.getWriter();
        try {
            // Obtenemos el tipo de reenvió
            String method = request.getParameter("dropdown");

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Request Dispatcher</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>");
            out.println("Header");
            out.println("</h1>");
            out.println("<hr>");

            // Agregamos un atributo que es enviado al otro Servlet
            request.setAttribute("currentDate", new Date());

            /*
             * Es importante que el path empieze con / porque sino puede generar fallas
             */
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/HelloWorldServlet");
            if ("Include".equals(method)) {
                System.out.println("Antes de llamar al include");
                rd.include(request, response);
                System.out.println("Después de llamar al include");
                out.println("<h1>");
                out.println("Footer");
                out.println("</h1>");
            } else {
                System.out.println("Antes de llamar al forward");
                rd.forward(request, response);
                System.out.println("Después de llamar al forward");
                out.println("<h1>");
                out.println("Footer");
                out.println("</h1>");
            }
        } finally {
            out.println("</body>");
            out.println("</html>");
            System.out.println("Cerrando Stream de datos");
            out.close();
        }
    }
}
