package ar.com.javacuriosities.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloWorldController {

    @RequestMapping("/welcome")
    public ModelAndView helloWorld() {
        String message = "Cosme Fulanito";
        return new ModelAndView("hello_world", "message", message);
    }
}
