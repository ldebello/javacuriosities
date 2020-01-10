package ar.com.javacuriosities.struts.mappingdispatchaction;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomMappingDispatchAction extends MappingDispatchAction {

    public ActionForward generateXml(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("message", "XML generado");

        return mapping.findForward("ok");
    }

    public ActionForward generateExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("message", "Excel generado");

        return mapping.findForward("ok");
    }
}