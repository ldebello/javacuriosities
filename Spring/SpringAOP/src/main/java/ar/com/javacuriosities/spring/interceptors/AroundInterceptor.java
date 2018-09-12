package ar.com.javacuriosities.spring.interceptors;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class AroundInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Object obj;
        System.out.println("Before business logic!!!");

        obj = methodInvocation.proceed();

        System.out.println("After business logic!!!");
        return obj;
    }
}
