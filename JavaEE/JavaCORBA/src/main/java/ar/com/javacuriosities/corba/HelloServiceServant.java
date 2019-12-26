package ar.com.javacuriosities.corba;

import HelloApp.HelloServicePOA;
import org.omg.CORBA.ORB;

public class HelloServiceServant extends HelloServicePOA {
    private ORB orb;

    public void setORB(ORB orb_val) {
        orb = orb_val;
    }

    @Override
    public String sayHello() {
        return "Hello World!!!";
    }

    public void shutdown() {
        orb.shutdown(false);
    }
}
