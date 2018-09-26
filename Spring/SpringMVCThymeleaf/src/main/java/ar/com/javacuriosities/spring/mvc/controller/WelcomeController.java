package ar.com.javacuriosities.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeController {

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public String index(Model model) {
        model.addAttribute("message", "Hello Spring MVC 5!");
        return "index";
    }
}
