package ar.com.javacuriosities.sessions;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * La técnica HTTPSession nos permite hacer tracking de la session pero la información de la sesión es persistida del lado servidor.
 *
 * Ventajas:
 *
 * - Es una técnica simple para mantener estado.
 * - El cliente solo tiene que mantener una cookie con el ID se su sesión JSESSIONID
 *
 * Desventajas:
 *
 * - No funcionan si el cliente desactivo las cookies.
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            // Recuperamos el nombre de usuario
            String user = request.getParameter("txtUser");

            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("Bienvenido " + user);
            out.println("<form action='WelcomeServlet'>");
            out.println("<input type='submit' value='GO'>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
