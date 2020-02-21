package ar.com.javacuriosities.spring.rs;

import ar.com.javacuriosities.spring.rs.converters.YamlJackson2HttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@SpringBootApplication
public class Main implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new YamlJackson2HttpMessageConverter());
    }
}