package ar.com.javacuriosities.servlets;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ListenerApp implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Iniciando App");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Destruyendo App");
    }
}
