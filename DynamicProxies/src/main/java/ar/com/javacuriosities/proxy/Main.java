package ar.com.javacuriosities.proxy;

import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) {
        LoggingService loggingService = new DefaultLoggingService();

        LoggingService proxyLoggingService = (LoggingService) Proxy.newProxyInstance(
                Main.class.getClassLoader(), new Class[]{LoggingService.class},
                new TrackingInvocationHandler(loggingService));

        loggingService.log("Hello World!");

        proxyLoggingService.log("Hello World!");
    }
}
