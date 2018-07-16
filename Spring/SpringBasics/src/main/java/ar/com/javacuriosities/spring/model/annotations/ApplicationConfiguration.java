package ar.com.javacuriosities.spring.model.annotations;

import ar.com.javacuriosities.spring.model.xml.SimpleBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"ar.com.javacuriosities.spring.model.annotations"})
public class ApplicationConfiguration {

    @Bean
    public SimpleBean simpleBean() {
        SimpleBean simpleBean = new SimpleBean();
        simpleBean.setMessage("Hello World");
        return simpleBean;
    }

    @Bean
    public SimpleBeanMessage messageBean() {
        SimpleBeanMessage simpleBeanMessage = new SimpleBeanMessage();
        simpleBeanMessage.setMessage("Simple Bean Message");
        return simpleBeanMessage;
    }
}
