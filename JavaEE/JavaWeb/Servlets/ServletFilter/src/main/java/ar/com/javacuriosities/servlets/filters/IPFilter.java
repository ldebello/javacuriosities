package ar.com.javacuriosities.servlets.filters;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class IPFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("IP Filter - Inicio");
        
        // Código a ejecutar antes de ejecutar la petición original
        System.out.println("IP: " + request.getRemoteAddr());

        try {
            // Invocación al llamado original
            chain.doFilter(request, response);
        } catch (Throwable t) {
            // Log and Handle exception
            t.printStackTrace();
            // Deberíamos responder con una pagina de error
        }
                
        // Código a ejecutar luego que la petición fue procesada
        System.out.println("IP Filter - Fin");
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
        // Obtenemos el parámetro
        String email = filterConfig.getInitParameter("Email");

        // Imprimimos el valor
        System.out.println("Parametro Inicial: " + email);
    }
}