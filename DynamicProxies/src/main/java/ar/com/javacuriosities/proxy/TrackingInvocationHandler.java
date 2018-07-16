package ar.com.javacuriosities.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TrackingInvocationHandler implements InvocationHandler {

    private Object target;

    public TrackingInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.nanoTime();
        Object result = method.invoke(target, args);
        long elapsed = System.nanoTime() - start;

        System.out.println(String.format("Executing %s finished in %s ns", method.getName(), elapsed));

        return result;
    }
}