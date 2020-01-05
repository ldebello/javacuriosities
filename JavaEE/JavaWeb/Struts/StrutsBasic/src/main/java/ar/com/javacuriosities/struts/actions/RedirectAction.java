package ar.com.javacuriosities.struts.actions;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ActionRedirect redirect = new ActionRedirect(mapping.findForward("genericRedirect"));
        redirect.addParameter("parameter1", "luisdebello.cursos@gmail.com");
        redirect.addParameter("parameter2", "Cosme Fulanito");
        return redirect;
    }
}