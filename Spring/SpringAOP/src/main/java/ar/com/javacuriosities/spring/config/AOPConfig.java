package ar.com.javacuriosities.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"ar.com.javacuriosities.aop"})
public class AOPConfig {

}
