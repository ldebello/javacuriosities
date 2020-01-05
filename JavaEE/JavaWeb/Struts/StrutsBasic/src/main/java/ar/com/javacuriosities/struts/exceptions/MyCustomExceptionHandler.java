package ar.com.javacuriosities.struts.exceptions;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyCustomExceptionHandler extends ExceptionHandler {

    @Override
    public ActionForward execute(Exception ex, ExceptionConfig ae, ActionMapping mapping, ActionForm formInstance, HttpServletRequest request, HttpServletResponse response) throws ServletException {
        System.out.println(ex);
        return super.execute(ex, ae, mapping, formInstance, request, response);
    }
}