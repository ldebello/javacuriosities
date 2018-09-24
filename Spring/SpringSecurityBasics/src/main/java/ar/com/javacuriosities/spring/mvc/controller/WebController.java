package ar.com.javacuriosities.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebController {


    @RequestMapping(value="/", method = RequestMethod.GET)
    public String visitHomePage() {
        return "index";
    }

    @RequestMapping(value="/admin", method = RequestMethod.GET)
    public String visitAdministratorPage(ModelMap modelObject) {
        modelObject.addAttribute("title", "Administrator Control Panel");
        modelObject.addAttribute("message", "This Page Demonstrates How To Use Spring Security!");

        return "admin";
    }
}