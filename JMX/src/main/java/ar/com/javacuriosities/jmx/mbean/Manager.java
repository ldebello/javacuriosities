package ar.com.javacuriosities.jmx.mbean;


public class Manager implements ManagerMBean {

    private String name;

    @Override
    public void execute(String command) {
        System.out.println(name + " executing " + command);
    }

    @Override
    public String getName() {
        System.out.println("Getting - Manager bean name: " + this.name);
        return name;
    }

    @Override
    public void setName(String name) {
        System.out.println("Setting - Manager bean name: " + name);
        this.name = name;
    }

}