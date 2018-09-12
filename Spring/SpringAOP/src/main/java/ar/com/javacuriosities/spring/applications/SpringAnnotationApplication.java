package ar.com.javacuriosities.spring.applications;

import ar.com.javacuriosities.spring.services.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringAnnotationApplication {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application_context_aspect_annotations.xml");

        Configuration configuration = context.getBean("configuration", Configuration.class);

        configuration.configure();
        configuration.configure("Hello World");
        try {
            configuration.configureWithError();
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        }
        configuration.configureWithReturn();
        configuration.configureWithAnnotation();
    }
}
