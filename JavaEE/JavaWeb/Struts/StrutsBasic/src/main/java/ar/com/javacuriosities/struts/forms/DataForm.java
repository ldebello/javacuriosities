package ar.com.javacuriosities.struts.forms;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;

public class DataForm extends ActionForm {

    private String name = "";
    private String levelOfaccess = "";
    private String defaultOption;

    public DataForm() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevelOfaccess() {
        return levelOfaccess;
    }

    public void setLevelOfaccess(String levelOfaccess) {
        this.levelOfaccess = levelOfaccess;
    }

    public String getDefaultOption() {
        return defaultOption;
    }

    public void setDefaultOption(String defaultOption) {
        this.defaultOption = defaultOption;
    }

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();

        System.out.println("Nombre de usuario: " + getName());
        // El username no debe estar vacía...
        if (getName() == null || getName().length() < 1) {
            errors.add("name", new ActionMessage("login.error.name.empty"));
        }


        return errors;
    }
}
