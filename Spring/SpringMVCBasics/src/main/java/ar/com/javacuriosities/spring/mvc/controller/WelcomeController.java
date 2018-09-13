package ar.com.javacuriosities.spring.mvc.controller;

import ar.com.javacuriosities.spring.mvc.model.Message;
import ar.com.javacuriosities.spring.mvc.model.MessageWithValidation;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class WelcomeController {

    private static final String VIEW_NAME = "welcome";

    @RequestMapping(method = RequestMethod.GET, path = "/welcome")
    public ModelAndView welcome() {
        String message = "Cosme Fulanito";
        return new ModelAndView(VIEW_NAME, "message", message);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/welcome/{name}")
    public ModelAndView welcomeWithPathParam(@PathVariable("name") String name) {
        return new ModelAndView(VIEW_NAME, "message", name);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/welcome/param")
    public ModelAndView welcomeWithRequestParam(@RequestParam("name") String name) {
        return new ModelAndView(VIEW_NAME, "message", name);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/welcome/payload/simple")
    public ModelAndView welcomeWithRequestBodySimpleType(@RequestBody String payload) {
        return new ModelAndView(VIEW_NAME, "message", payload);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/welcome/payload/object", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView welcomeWithRequestBodyObjectType(@RequestBody Message payload) {
        return new ModelAndView(VIEW_NAME, "message", payload);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/welcome/payload/object/validation", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView welcomeWithRequestBodyObjectTypeAndValidation(@RequestBody @Valid MessageWithValidation payload) {
        return new ModelAndView(VIEW_NAME, "message", payload);
    }
}
