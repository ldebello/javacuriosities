package ar.com.javacuriosities.spring.aspect.xml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

// Los parámetros JointPoint son opcionales, es por si quiero o necesito información adicional
public class InstallationAspect {

    public void beforeAdvice(JoinPoint joinPoint) {
        System.out.println("Message before from aspect!!!");
    }

    public void afterAdvice(JoinPoint joinPoint) {
        System.out.println("Message after from aspect!!!");
    }

    public void afterReturningAdvice(JoinPoint joinPoint, Object retVal) {
        if (retVal != null) {
            System.out.println("Returning:" + retVal.toString());
        }
    }

    public void afterThrowingAdvice(JoinPoint joinPoint, Throwable ex) {
        System.out.println("There has been an exception: " + ex.toString());
    }

    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("Message before - around aspect!!!");
        Object value = null;

        try {
            value = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("Message after - around aspect!!!");
        return value;
    }
}
