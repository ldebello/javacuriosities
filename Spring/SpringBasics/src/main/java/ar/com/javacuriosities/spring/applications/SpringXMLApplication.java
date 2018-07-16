package ar.com.javacuriosities.spring.applications;

import ar.com.javacuriosities.spring.model.annotations.SimpleBeanWithAnnotations;
import ar.com.javacuriosities.spring.model.xml.SimpleBean;
import ar.com.javacuriosities.spring.model.xml.SimpleBeanCollections;
import ar.com.javacuriosities.spring.model.xml.SimpleBeanContainer;
import ar.com.javacuriosities.spring.model.xml.SimpleBeanLifeCycle;
import ar.com.javacuriosities.spring.model.xml.SimpleBeanPrototype;
import ar.com.javacuriosities.spring.model.xml.SimpleBeanWithParameters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringXMLApplication {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application_context.xml");

        System.out.println("***** Simple Bean *****");
        SimpleBean simpleBean = context.getBean("simpleBean", SimpleBean.class);
        System.out.println(simpleBean.getMessage());

        System.out.println("***** Simple Bean Container *****");
        SimpleBeanContainer simpleBeanContainer = context.getBean("simpleBeanContainer", SimpleBeanContainer.class);

        System.out.println("Simple Bean - hashCode: " + simpleBean.hashCode());
        System.out.println("Bean 1 - hashCode: " + simpleBeanContainer.getBean1().hashCode());
        System.out.println("Bean 2 - hashCode: " + simpleBeanContainer.getBean2().hashCode());

        System.out.println("***** Simple Bean With Parameters *****");
        SimpleBeanWithParameters simpleBeanWithParameters = context.getBean("simpleBeanWithParameters", SimpleBeanWithParameters.class);

        System.out.println(simpleBeanWithParameters.getEmployees());
        System.out.println(simpleBeanWithParameters.getMultiplier());

        System.out.println("***** Simple Bean With Annotations *****");
        SimpleBeanWithAnnotations simpleBeanWithAnnotations = context.getBean("simpleBeanWithAnnotation", SimpleBeanWithAnnotations.class);
        System.out.println(simpleBeanWithAnnotations.getSimpleBean().getMessage());

        System.out.println("***** Simple Bean Collections *****");
        SimpleBeanCollections simpleBeanCollections = context.getBean("simpleBeanCollections", SimpleBeanCollections.class);
        System.out.println("Courses: " + simpleBeanCollections.getCourses());
        System.out.println("Students: " + simpleBeanCollections.getStudents());

        System.out.println("***** Simple Bean Life Cycle *****");
        SimpleBeanLifeCycle simpleBeanLifeCycle = context.getBean("simpleBeanLifeCycle", SimpleBeanLifeCycle.class);

        System.out.println("***** Simple Bean Prototype *****");
        SimpleBeanPrototype simpleBeanPrototype1 = context.getBean("simpleBeanPrototype", SimpleBeanPrototype.class);
        SimpleBeanPrototype simpleBeanPrototype2 = context.getBean("simpleBeanPrototype", SimpleBeanPrototype.class);

        System.out.println("Prototype 1 - hashCode: " + simpleBeanPrototype1.hashCode());
        System.out.println("Prototype 2 - hashCode: " + simpleBeanPrototype2.hashCode());
    }
}
