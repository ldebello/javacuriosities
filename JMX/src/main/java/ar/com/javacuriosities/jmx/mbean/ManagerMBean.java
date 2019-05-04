package ar.com.javacuriosities.jmx.mbean;

public interface ManagerMBean {

    void execute(String command);

    String getName();

    void setName(String name);
}