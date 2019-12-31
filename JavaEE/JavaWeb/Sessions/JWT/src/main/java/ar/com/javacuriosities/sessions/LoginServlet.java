package ar.com.javacuriosities.sessions;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            String user = request.getParameter("txtUser");
            String jwt = createSignedJWT(user, "manager");

            Cookie cookie = new Cookie("JWT-SESSION-ID", jwt);

            response.addCookie(cookie);

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("Bienvenido " + user);
            out.println("<form action='WelcomeServlet'>");
            out.println("<input type='submit' value='JWT'>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private String createSignedJWT(String user, String role) {
        return JWT.create().withClaim("user", user).withClaim("role", role).sign(Algorithm.HMAC256("secret"));
    }
}
