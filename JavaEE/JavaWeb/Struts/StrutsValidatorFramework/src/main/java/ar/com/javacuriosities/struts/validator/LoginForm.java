package ar.com.javacuriosities.struts.validator;

import org.apache.struts.validator.ValidatorForm;

public class LoginForm extends ValidatorForm {

    private String name;
    private String password;
    private String passwordCheck;
    private String email;

    public LoginForm() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordCheck() {
        return passwordCheck;
    }

    public void setPasswordCheck(String passwordCheck) {
        this.passwordCheck = passwordCheck;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
