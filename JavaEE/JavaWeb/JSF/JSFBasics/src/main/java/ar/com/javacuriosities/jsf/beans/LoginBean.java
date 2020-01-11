package ar.com.javacuriosities.jsf.beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named("loginBean")
@RequestScoped
public class LoginBean {

    private String userName;
    private String password;

    private UIComponent component;

    public LoginBean() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UIComponent getComponent() {
        return component;
    }

    public void setComponent(UIComponent component) {
        this.component = component;
    }

    public String validar() {
        boolean error = false;
        if (userName == null || userName.trim().isEmpty()) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario no valido", null);
            FacesContext.getCurrentInstance().addMessage(component.getClientId(), message);
            error = true;
        }

        if (password == null || password.trim().isEmpty()) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Password no valido", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            error = true;
        }

        return !error ? "welcome" : "login";
    }
}
