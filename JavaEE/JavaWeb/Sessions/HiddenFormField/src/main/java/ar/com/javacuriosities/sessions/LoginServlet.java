package ar.com.javacuriosities.sessions;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Esta técnica utiliza un campo oculto en los formularios para mantener datos de la sesión.
 *
 * Ventajas:
 *
 * - Siempre funciona sin importar si las cookies están habilitadas o no.
 *
 * Desventajas:
 *
 * - Cada pagina requiere un formulario.
 * - Solo información en formato texto puede ser enviada.
 * - La lógica es mantenida por el servidor.
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
            String user = request.getParameter("txtUsuario");

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<form action='WelcomeServlet' method='POST'>");
            out.println("<input type='hidden' name='txtUsuario' value='" + user + "'>");
            out.println("<input type='submit' value='Hidden Form'>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }
    }

}
