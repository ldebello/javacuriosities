package ar.com.javacuriosities.servlets.listeners;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/*
 * Este listener es invocado al crearse y destruirse 
 * las peticiones
 */
@WebListener
public class JavaWebServletRequestListener implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("Request creado");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("Request destruido");
    }
}
