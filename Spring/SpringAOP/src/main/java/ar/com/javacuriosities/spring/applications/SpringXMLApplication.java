package ar.com.javacuriosities.spring.applications;

import ar.com.javacuriosities.spring.services.Business;
import ar.com.javacuriosities.spring.services.Installation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringXMLApplication {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application_context.xml");

        System.out.println("****** Proxy Bean ******");

        System.out.println("**** After Return Interceptor ****");
        Business businessWithAfterReturnInterceptor = context.getBean("businessWithAfterReturnInterceptor", Business.class);
        businessWithAfterReturnInterceptor.execute();

        System.out.println("**** Around Interceptor ****");
        Business businessWithAroundInterceptor = context.getBean("businessWithAroundInterceptor", Business.class);
        businessWithAroundInterceptor.execute();

        System.out.println("**** Before Interceptor ****");
        Business businessWithBeforeInterceptor = context.getBean("businessWithBeforeInterceptor", Business.class);
        businessWithBeforeInterceptor.execute();

        System.out.println("**** Throws Interceptor ****");
        Business businessWithThrowsInterceptor = context.getBean("businessWithThrowsInterceptor", Business.class);
        try {
            businessWithThrowsInterceptor.executeWithException();
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        }

        System.out.println("****** Aspects ******");

        Installation installation = context.getBean("installation", Installation.class);

        System.out.println("**** Pointcut without parameter ****");
        installation.start();

        System.out.println("**** Pointcut multiples parameters ****");
        installation.start("Hello World"); // Multiples parameters pointcut

        System.out.println("**** Pointcut exception ****");
        try {
            installation.startWithError();
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        }

        System.out.println("**** Pointcut after return ****");
        installation.startWithReturn();
    }
}
