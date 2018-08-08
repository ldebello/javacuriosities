package ar.com.javacuriosities.spring.mvc;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.webapp.WebAppContext;

public class Main {
    public static void main(String[] args) {
        try {
            Server server = new Server(8080);

            HandlerCollection handlers = new HandlerCollection();

            WebAppContext webapp = new WebAppContext();

            webapp.setResourceBase(Main.class.getProtectionDomain().getCodeSource().getLocation().toString());
            webapp.setContextPath("/mvc");
            webapp.setDefaultsDescriptor("web.xml");

            handlers.addHandler(webapp);

            server.setHandler(handlers);

            server.start();
            System.out.println("Started!");
            server.join();
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        }
    }
}
