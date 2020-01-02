package ar.com.javacuriosities.servlets.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/*
 * Este listener es invocado al crearse y destruirse 
 * (respectivamente) el contexto de la aplicación (WAR).
 */
@WebListener
public class JavaWebServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Aplicación iniciada");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Aplicación destruida");
    }
}
