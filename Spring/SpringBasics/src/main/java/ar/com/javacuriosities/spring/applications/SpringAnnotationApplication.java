package ar.com.javacuriosities.spring.applications;

import ar.com.javacuriosities.spring.model.annotations.ApplicationConfiguration;
import ar.com.javacuriosities.spring.model.annotations.SimpleBeanInterface;
import ar.com.javacuriosities.spring.model.annotations.SimpleBeanMessage;
import ar.com.javacuriosities.spring.model.annotations.SimpleBeanWithAnnotations;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringAnnotationApplication {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        System.out.println("***** Simple Bean From Method *****");
        SimpleBeanMessage simpleBeanMessage = context.getBean("messageBean", SimpleBeanMessage.class);
        System.out.println(simpleBeanMessage.getMessage());

        System.out.println("***** Simple Bean From Component Scan *****");
        SimpleBeanWithAnnotations simpleBeanWithAnnotations = context.getBean("simpleBeanWithAnnotation", SimpleBeanWithAnnotations.class);
        System.out.println(simpleBeanWithAnnotations.getSimpleBean().getMessage());

        System.out.println("***** Simple Bean From Interface *****");
        SimpleBeanInterface simpleBeanInterface = context.getBean(SimpleBeanInterface.class);
        simpleBeanInterface.sayHello();
    }
}
