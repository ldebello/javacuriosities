package ar.com.javacuriosities.servlets.filters;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("Authentication Filter - Inicio");

        // Obtenemos nombre de usuario
        String userName = request.getParameter("txtName");

        System.out.println("El usuario es:" + userName);
        if ("CosmeFulanito".equals(userName)) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, "Usted no tiene permisos");
        }
        chain.doFilter(request, response);

        System.out.println("Authentication Filter - Fin");
    }

    /**
     * Este método es ejecutado cuando el Filtro se destruye
     */
    @Override
    public void destroy() {
    }

    /**
     * Este método es ejecutado cuando el Filtro se construye
     */
    @Override
    public void init(FilterConfig filterConfig) {
    }
}