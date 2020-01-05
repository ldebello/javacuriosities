package ar.com.javacuriosities.struts.actions;

import ar.com.javacuriosities.struts.forms.HelloWorldForm;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorldAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        HelloWorldForm currentForm = (HelloWorldForm) form;
        currentForm.setMessage("Hola Cosme Fulanito! Esto es Struts 1.3.10");
        return mapping.findForward("welcome");
    }
}