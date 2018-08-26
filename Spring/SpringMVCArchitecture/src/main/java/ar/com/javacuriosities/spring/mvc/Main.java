package ar.com.javacuriosities.spring.mvc;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.webapp.Configuration;
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
            Configuration.ClassList classlist = Configuration.ClassList.setServerDefault(server);
            classlist.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration","org.eclipse.jetty.annotations.AnnotationConfiguration");
            webapp.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",".*/[^/]*jstl.*\\.jar$");

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
