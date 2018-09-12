package ar.com.javacuriosities.spring.aspect.annotations;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class ConfigurationAspect {

    @Before("within(ar.com.javacuriosities.spring.services.Configuration)")
    public void logMessageBefore() {
        System.out.println("Logging Before Message");
    }

    @After("within(ar.com.javacuriosities.spring.services.Configuration)")
    public void logMessageAfter() {
        System.out.println("Logging After Message");
    }

    @After("args(parameter)")
    public void logMessageMatchingMethodByType(String parameter) {
        System.out.println("Logging After Message. String argument: " + parameter);
    }

    @AfterThrowing("within(ar.com.javacuriosities.spring.services.Configuration)")
    public void logMessageAfterThrow(JoinPoint joinPoint) {
        System.out.println("Exception thrown in Configuration method: " + joinPoint.toString());
    }

    @AfterThrowing(value = "within(ar.com.javacuriosities.spring.services.Configuration)", throwing = "t")
    public void logMessageAfterThrow(JoinPoint joinPoint, Throwable t) {
        System.out.println("Exception thrown in Configuration method: " + joinPoint.toString() + " exception: " + t.getMessage());
    }

    @AfterReturning(value = "execution(* configureWithReturn())", returning = "retVal")
    public void logMessageAfterReturn(JoinPoint joinPoint, Object retVal) {
        System.out.println("configureWithReturn method executed, return value:" + retVal);
    }
}
