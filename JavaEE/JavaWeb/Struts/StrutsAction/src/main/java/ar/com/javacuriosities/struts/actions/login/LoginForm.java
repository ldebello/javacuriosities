package ar.com.javacuriosities.struts.actions.login;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;

public class LoginForm extends ActionForm {

    private String user;
    private String password;

    public LoginForm() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String usuario) {
        this.user = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (user == null || user.length() < 1) {
            errors.add("user", new ActionMessage("errors.user.required"));
        }
        if (password == null || password.length() < 1) {
            errors.add("password", new ActionMessage("errors.password.required"));
        }
        return errors;
    }
}
