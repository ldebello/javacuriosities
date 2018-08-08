package ar.com.javacuriosities.spring.mvc.view;

import ar.com.javacuriosities.spring.mvc.model.ModelAndView;

public class ViewResolver {

    public static String getView(ModelAndView modelAndView) {
        return "/" + modelAndView.getViewName() + ".jsp";
    }
}
