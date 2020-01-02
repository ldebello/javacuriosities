package ar.com.javacuriosities.servlets.listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/*
 * Este listener es invocado al agregarse, 
 * eliminarse o modificarse (respectivamente) 
 * un atributo de una sesión.
 */
@WebListener
public class JavaWebHttpSessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("Sesión creada");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("Sesión destruida");
    }
}
