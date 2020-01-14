package ar.com.javacuriosities.jsf.lifecycle;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("lifecycleManagedBean")
@RequestScoped
public class LifecycleManagedBean {

    private PersonaForm personaForm = new PersonaForm();

    public LifecycleManagedBean() {

    }

    public PersonaForm getPersonaForm() {
        return personaForm;
    }

    public void setPersonaForm(PersonaForm personaForm) {
        this.personaForm = personaForm;
    }

}