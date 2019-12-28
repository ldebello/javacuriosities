package ar.com.javacuriosities.sessions;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

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
            String user = request.getParameter("txtUsuario");

            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            // Creamos una cookie que vuelve en el response
            // El tamaño maximo es de 4 KB (4096 caracteres) y 20 cookies por Web Server (300 cookies máximas)
            Cookie cookie = new Cookie("user", user);
            // Configuramos el tiempo a 30 minutos
            // El valor por default es -1 o sea hasta que el browser se cierre
            cookie.setMaxAge(30 * 60);

            // Para que el comentario funcione la version de 
            // la cookie tiene que ser Version 1 (by RFC 2109)
            // por defecto se crean con Version 0 (by Netscape) para asegurar
            // la compatibilidad
            cookie.setComment("Comentario prueba");

            // Agregamos la cookie a la respuesta
            response.addCookie(cookie);

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("Bienvenido " + user);
            out.println("<form action='WelcomeServlet'>");
            out.println("<input type='submit' value='Cookies'>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
