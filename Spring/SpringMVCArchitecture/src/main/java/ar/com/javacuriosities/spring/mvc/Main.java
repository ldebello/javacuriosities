package ar.com.javacuriosities.spring.mvc;

import org.apache.jasper.servlet.JspServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.webapp.WebAppContext;

public class Main {
    public static void main(String[] args) {
        try {
            Server server = new Server(8080);

            HandlerCollection handlers = new HandlerCollection();

            WebAppContext webapp = new WebAppContext();

            webapp.setContextPath("/");
            webapp.setResourceBase(Main.class.getProtectionDomain().getCodeSource().getLocation().toString());

            // Adding JSP support
            webapp.setClassLoader(Thread.currentThread().getContextClassLoader());
            webapp.addServlet(JspServlet.class, "*.jsp");

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
