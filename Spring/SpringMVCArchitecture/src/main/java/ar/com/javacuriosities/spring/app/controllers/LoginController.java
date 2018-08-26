package ar.com.javacuriosities.spring.app.controllers;


import ar.com.javacuriosities.spring.app.model.User;
import ar.com.javacuriosities.spring.mvc.controller.AbstractController;
import ar.com.javacuriosities.spring.mvc.model.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginController extends AbstractController {

    @Override
    protected ModelAndView handleRenderRequestInternal(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("welcome");
        User user = new User();

        user.setId(Integer.valueOf(1));
        user.setName(request.getParameter("txtUser"));

        modelAndView.addObject("user", user);

        return modelAndView;
    }
}