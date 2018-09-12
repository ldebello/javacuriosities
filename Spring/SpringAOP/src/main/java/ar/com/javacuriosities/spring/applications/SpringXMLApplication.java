package ar.com.javacuriosities.spring.applications;

import ar.com.javacuriosities.spring.services.Business;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringXMLApplication {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application_context.xml");

        Business businessWithAfterReturnInterceptor = context.getBean ("businessWithAfterReturnInterceptor", Business.class);
        Business businessWithAroundInterceptor = context.getBean ("businessWithAroundInterceptor", Business.class);
        Business businessWithBeforeInterceptor = context.getBean ("businessWithBeforeInterceptor", Business.class);
        Business businessWithThrowsInterceptor = context.getBean ("businessWithThrowsInterceptor", Business.class);

        businessWithAfterReturnInterceptor.execute();

        businessWithAroundInterceptor.execute();

        businessWithBeforeInterceptor.execute();
        try {
            businessWithThrowsInterceptor.executeWithException();
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        }

    }
}
