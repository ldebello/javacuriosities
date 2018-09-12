package ar.com.javacuriosities.spring.aspect.annotations;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ConfigurationAspect {

    @Pointcut("execution(* ar.com.javacuriosities.spring.services.Configuration.configureWithReturn())")
    public void getConfigureWithReturnPointcut() {
    }

    @Before("within(ar.com.javacuriosities.spring.services.Configuration)")
    public void logMessageBefore() {
        System.out.println("Logging before message");
    }

    @Before("@annotation(ar.com.javacuriosities.spring.annotations.Loggable)")
    public void logMessageBeforeIfMethodIsAnnotatedWithloggable() {
        System.out.println("Logging before message because if has Loggable annotation");
    }

    @Before("getConfigureWithReturnPointcut()")
    public void allServiceMethodsAdvice() {
        System.out.println("Before invoking configureWithReturn() method");
    }

    @After("within(ar.com.javacuriosities.spring.services.Configuration)")
    public void logMessageAfter() {
        System.out.println("Logging after message");
    }

    @After("args(parameter)")
    public void logMessageMatchingMethodByType(String parameter) {
        System.out.println("Logging after message. String argument: " + parameter);
    }

    @AfterThrowing("within(ar.com.javacuriosities.spring.services.Configuration)")
    public void logMessageAfterThrow(JoinPoint joinPoint) {
        System.out.println("Exception thrown in configuration method: " + joinPoint.toString());
    }

    @AfterThrowing(value = "within(ar.com.javacuriosities.spring.services.Configuration)", throwing = "t")
    public void logMessageAfterThrow(JoinPoint joinPoint, Throwable t) {
        System.out.println("Exception thrown in configuration method: " + joinPoint.toString() + " exception: " + t.getMessage());
    }

    @AfterReturning(value = "execution(* configureWithReturn())", returning = "retVal")
    public void logMessageAfterReturn(JoinPoint joinPoint, Object retVal) {
        System.out.println("configureWithReturn method executed. Return value:" + retVal);
    }

    @Around("execution(* ar.com.javacuriosities.spring.services.Configuration.configure())")
    public Object logMessageAroundConfigureMethod(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("Before invoking configure() method");

        Object value = null;
        try {
            value = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("After invoking configure() method");

        return value;
    }
}
