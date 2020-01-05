package ar.com.javacuriosities.struts.actions.dispatch;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class LanguageSelectAction extends DispatchAction {

    public ActionForward english(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute(Globals.LOCALE_KEY, Locale.ENGLISH);
        return mapping.findForward("success");
    }

    public ActionForward german(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute(Globals.LOCALE_KEY, Locale.GERMAN);
        return mapping.findForward("success");
    }

    public ActionForward france(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute(Globals.LOCALE_KEY, Locale.FRANCE);
        return mapping.findForward("success");
    }
}
