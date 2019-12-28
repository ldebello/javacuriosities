package ar.com.javacuriosities.sessions;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
            Cookie cookie = null;
            Cookie[] cookies = request.getCookies();
            HttpSession session = request.getSession();

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Welcome</title>");
            out.println("</head>");
            out.println("<body>");
            if (cookies != null) {
                out.println("<h2>Cookies</h2>");
                for (int i = 0; i < cookies.length; i++) {
                    cookie = cookies[i];
                    out.print("<b>Name: </b>" + cookie.getName() + ",  ");
                    out.print("<b>Value: </b>" + cookie.getValue() + " <br/>");
                }
            } else {
                out.println("<h2>No cookies founds</h2>");
            }
            out.println("<br>");
            out.println("Session: " + session.getId());
            out.println("<br>");
            out.println("User: " + session.getAttribute("user"));
            out.println("</body>");
            out.println("</html>");
        }
    }
}
