package ar.com.javacuriosities.spring.interceptors;

import org.springframework.aop.ThrowsAdvice;

public class ThrowsInterceptor implements ThrowsAdvice {

    public void afterThrowing(Exception ex) {
        System.out.println("Exception!!! ".concat(ex.getMessage()));
    }
}
