package ar.com.javacuriosities.sessions;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * La técnica URL Rewriting consiste en agregar un identificador en la url para poder mantener los datos de una sesión.
 *
 * Ventajas:
 *
 * - Funciona siempre ya que no depende si el browser tiene o no cookies habilitadas.
 *
 * Desventajas:
 *
 * - Solo funciona con links.
 * - Solo podemos mandar información en formato texto.
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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            // Recuperamos el nombre de usuario
            String user = request.getParameter("txtUser");

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("Bienvenido " + user);
            out.println("<a href='" + response.encodeURL("WelcomeServlet?user=" + user) + "'>Link</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
