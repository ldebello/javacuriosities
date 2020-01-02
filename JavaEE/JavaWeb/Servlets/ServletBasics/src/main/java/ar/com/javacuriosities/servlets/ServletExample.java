package ar.com.javacuriosities.servlets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/*
 * Los Servlets pueden ser definidos via Annotations o deben ser definidos
 * en el web.xml (Descriptor de despliegue)
 */
public class ServletExample extends HttpServlet {

    private String user;

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

    /*
     * El Servlet es instanciado automáticamente por el Servlet Container, si sobrescribimos
     * el init no debemos olvidarnos de llamar al super.init(servletConfig);
     */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        System.out.println("Iniciando Servlet");
        this.user = servletConfig.getInitParameter("User");

        // Usando el Major y Minor version podemos saber que version de Servlet implementa el servidor
        System.out.println("Major Version: " + servletConfig.getServletContext().getMajorVersion());
        System.out.println("Minor Version: " + servletConfig.getServletContext().getMinorVersion());
    }

    /*
     * Este método es agregado por el NetBeans los métodos doPost y doGet llaman a este método
     * para definir un punto común de lógica
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Establecemos el tipo de contenido
        response.setContentType("text/html;charset=UTF-8");

        // Obtenemos el stream de salida
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Information</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Servlet Protocol: " + request.getProtocol() + "</h2>");
            out.println("<h2>Servlet RequestURI: " + request.getRequestURI() + "</h2>");
            out.println("<h2>Servlet Method: " + request.getMethod() + "</h2>");
            out.println("<h2>Servlet Query String: " + request.getQueryString() + "</h2>");
            out.println("<h2>Servlet Is Secure: " + request.isSecure() + "</h2>");
            out.println("<h2>Servlet Parameter Name: " + request.getParameter("name") + "</h2>");
            out.println("<h2>Servlet Parameter Last Name: " + request.getParameter("lastName") + "</h2>");
            out.println("<h2>Servlet Init-Param: " + user + "</h2>");
            out.println("<h2>Servlet Context Email: " + getServletContext().getInitParameter("Email") + "</h2>");

            // Obtener el nombre de los parametros
            Enumeration<String> paramaterNames = request.getParameterNames();

            int parameterIndex = 0;
            while (paramaterNames.hasMoreElements()) {
                String parameterName = paramaterNames.nextElement();
                System.out.println("Parameter " + parameterIndex + ": " + parameterName);
                parameterIndex++;
            }


            out.println("</body>");
            out.println("</html>");
        }
    }

    /*
     * Este método es llamado cuando la instancia del Servlet es dada de baja
     */
    @Override
    public void destroy() {
        super.destroy();
    }
}
