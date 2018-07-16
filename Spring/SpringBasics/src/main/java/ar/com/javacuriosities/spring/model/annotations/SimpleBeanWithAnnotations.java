package ar.com.javacuriosities.spring.model.annotations;

import ar.com.javacuriosities.spring.model.xml.SimpleBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("simpleBeanWithAnnotation")
public class SimpleBeanWithAnnotations {

    @Autowired
    private SimpleBean simpleBean;

    public SimpleBean getSimpleBean() {
        return simpleBean;
    }

    public void setSimpleBean(SimpleBean simpleBean) {
        this.simpleBean = simpleBean;
    }
}
