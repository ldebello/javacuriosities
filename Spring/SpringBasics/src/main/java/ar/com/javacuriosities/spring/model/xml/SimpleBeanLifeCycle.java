package ar.com.javacuriosities.spring.model.xml;

public class SimpleBeanLifeCycle {

    public SimpleBeanLifeCycle() {
    }

    public void initialize() {
        System.out.println("Initialize method");
    }

    /*
     * Si queremos que el metodo destroy sea invocado como parte
     * del life cycle debemos registrar un shutdown hook en el context.
     * AbstractApplicationContext#registerShutdownHook
     */
    public void destroy() {
        System.out.println("Destroy method");
    }
}
