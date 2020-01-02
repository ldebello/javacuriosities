package ar.com.javacuriosities.servlets.listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/*
 * Este listener es invocado al agregarse, 
 * eliminarse o modificarse (respectivamente) 
 * un atributo de una sesión.
 */
@WebListener
public class JavaWebHttpSessionAttributesListener implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        System.out.println("Atributo de sesión agregado");
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        System.out.println("Atributo de sesión removido");
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        System.out.println("Atributo de sesión reemplazado");
    }
}
