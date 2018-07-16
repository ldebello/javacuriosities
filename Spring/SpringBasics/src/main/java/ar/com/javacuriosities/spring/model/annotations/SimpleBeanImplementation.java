package ar.com.javacuriosities.spring.model.annotations;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Scope("prototype")
@Component("simpleBeanAnnotations")
public class SimpleBeanImplementation implements SimpleBeanInterface {
    @Override
    public void sayHello() {
        System.out.println("Hello World!!!");
    }

    @PostConstruct
    public void initialize() {
        System.out.println("PostConstruct method");
    }

    /**
     * Aunque usáramos AbstractApplicationContext#registerShutdownHook
     * este método no se ejecutaría, ya que los PreDestroy no son ejecutados cuando el scope
     * es prototype
     */
    @PreDestroy
    public void destroy() {
        System.out.println("PreDestroy method");
    }
}
