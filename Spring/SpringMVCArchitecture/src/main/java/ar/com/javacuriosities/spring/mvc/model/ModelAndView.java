package ar.com.javacuriosities.spring.mvc.model;

import java.util.HashMap;
import java.util.Map;

public class ModelAndView {

    private String viewName;
    private Map<String, Object> model;

    public ModelAndView(String viewName) {
        this.viewName = viewName;
    }

    public ModelAndView addObject(String attributeName, Object attributeValue) {
        getModel().put(attributeName, attributeValue);
        return this;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Map<String, Object> getModel() {
        if (model == null) {
            model = new HashMap<String, Object>();
        }
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}