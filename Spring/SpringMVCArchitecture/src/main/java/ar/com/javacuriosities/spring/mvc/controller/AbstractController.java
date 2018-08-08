package ar.com.javacuriosities.spring.mvc.controller;

import ar.com.javacuriosities.spring.mvc.model.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractController {
    public ModelAndView handleRenderRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return handleRenderRequestInternal(request, response);
    }

    protected abstract ModelAndView handleRenderRequestInternal(HttpServletRequest request, HttpServletResponse response);
}
