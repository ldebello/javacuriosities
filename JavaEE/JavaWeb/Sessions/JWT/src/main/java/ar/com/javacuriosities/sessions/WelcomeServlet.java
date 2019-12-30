package ar.com.javacuriosities.sessions;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class WelcomeServlet extends HttpServlet {

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
            Cookie cookie = getCookieFromRequest(request, "JWT-SESSION-ID");

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Welcome</title>");
            out.println("</head>");
            out.println("<body>");
            if (cookie != null) {
                String jwt = cookie.getValue();
                JWTVerifier verifier = JWT.require(Algorithm.HMAC256("secret")).build();

                verifier.verify(jwt).getClaims().forEach((key, value) -> {
                    out.print("<b>Name: </b>" + key + " ");
                    out.print("<b>Value: </b>" + value.asString() + " <br/>");
                });
            } else {
                out.println("<h2>No token found</h2>");
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    private Cookie getCookieFromRequest(HttpServletRequest request, String name) {
        Cookie cookie = null;
        if (request != null) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (int i = 0; i < cookies.length; i++) {
                    Cookie currentCookie = cookies[i];
                    if (currentCookie != null && currentCookie.getName().equals(name)) {
                        cookie = currentCookie;
                    }
                }
            }
        }
        return cookie;
    }
}
