package ar.com.javacuriosities.jsf.validation.model;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named("validationData")
@RequestScoped
public class ValidationData {

    private String name;
    private int age;
    private String address;
    private String code;
    private String email;

    public ValidationData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String applicationLevelValidations() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (address == null || address.isEmpty()) {
            // Aca se agrega un mensaje para un Client Id
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Dirección esta vacía");
            message.setDetail("Dirección esta vacía, por favor ingrese algo valido");
            context.addMessage("formulario:address", message);

            // Aquí agregamos un mensaje sin ningún Client Id
            message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Mensaje global");
            message.setDetail("Mensaje global, porque no esta atado a ningún Client Id");
            context.addMessage(null, message);
            return "error";
        }
        return "with_validation";
    }

    public String backingBeanValidations(FacesContext context, UIComponent component, Object value) {
        String myValue = (String) value;
        if (myValue == null || myValue.isEmpty() || "1".equals(myValue)) {
            // Aca se agrega un mensaje para un Client Id que sale del componente
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("El código no es valido");
            message.setDetail("El código no es valido, ingrese algo distinto de 1");
            context.addMessage(component.getClientId(), message);
            return "without_validation";
        }
        return "with_validation";
    }
}
