package ar.com.javacuriosities.struts.actions.wildcard;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserAction extends MappingDispatchAction {

    public ActionForward createUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("CREATE");
        return mapping.findForward("view");
    }

    public ActionForward refreshUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("REFRESH");
        return mapping.findForward("view");
    }

    public ActionForward updateUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("UPDATE");
        return mapping.findForward("view");
    }

    public ActionForward deleteUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("DELETE");
        return mapping.findForward("view");
    }
}
