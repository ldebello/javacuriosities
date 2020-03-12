package ar.com.javacuriosities.jsf.beans;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@Named
@SessionScoped
public class LoginView implements Serializable {

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() {
        if ("admin".equals(username) && "123456".equals(password)) {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.setAttribute("logged", true);
            session.setAttribute("username", username);
            return "welcome";
        } else {
            FacesContext.getCurrentInstance().addMessage("loginForm", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Message", "Incorrect username or password"));
        }
        return "index";
    }

    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "index?faces-redirect=true";
    }
}
