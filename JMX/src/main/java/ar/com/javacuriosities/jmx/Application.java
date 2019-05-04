package ar.com.javacuriosities.jmx;


import ar.com.javacuriosities.jmx.mbean.Manager;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/*
 * La idea de JMX (Java Management Extensions) fue introducida en Java 1.5 y fue bien aceptado
 * en la comunidad como una forma simple de management en real time con el concepto de MBean.
 *
 * La arquitectura cuenta de 3 partes:
 *
 * Instrumentation: Son los MBean registrados en el JMX Agent.
 * JMX Agent: El componente core (MbeanServer) que mantiene un registro de los MBean y da una interfaz de acceso.
 * Remote Management: Un cliente que consume esto, por ejemplo jConsole.
 *
 * La construir un MBean necesitamos (Standard way):
 *
 * Una interfaz con el sufijo MBean (i.e: ManagerMBean)
 * Una implementación con el mismo nombre que la interfaz pero sin el sufijo
 * Registrarlo en el server por medio de un ObjectName(domain:key), El "domain" puede ser cualquier cosa pero suele ser un package y la key es una lista separada por "," de "key=value"
 */
public class Application {

    public static void main(String[] args) {
        System.out.println("JMX tutorial");

        ObjectName objectName = null;
        try {
            objectName = new ObjectName("ar.com.javacuriosities:type=basic,name=manager");
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        }

        MBeanServer server = ManagementFactory.getPlatformMBeanServer();

        Manager managerObject = new Manager();

        try {
            server.registerMBean(managerObject, objectName);
        } catch (InstanceAlreadyExistsException | MBeanRegistrationException | NotCompliantMBeanException e) {
            e.printStackTrace();
        }

        System.out.println("Registration for Manager with the platform server was successful");
        System.out.println("Open jConsole to access Manager");

        // Evitamos que la aplicación termine
        while (true) {
        }
    }

}