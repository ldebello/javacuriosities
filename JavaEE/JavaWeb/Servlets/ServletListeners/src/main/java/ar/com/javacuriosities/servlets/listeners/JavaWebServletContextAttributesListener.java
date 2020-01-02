package ar.com.javacuriosities.servlets.listeners;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

/*
 * Este listener es invocado al agregar, 
 * eliminar o modificar (respectivamente) 
 * un atributo del contexto de la aplicación.
 */
@WebListener
public class JavaWebServletContextAttributesListener implements ServletContextAttributeListener {

    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        System.out.println("Atributo de aplicación agregado");
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
        System.out.println("Atributo de aplicación removido");
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {
        System.out.println("Atributo de aplicación reemplazado");
    }
}
