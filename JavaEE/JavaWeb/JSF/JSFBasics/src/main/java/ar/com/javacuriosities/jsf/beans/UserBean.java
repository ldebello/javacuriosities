package ar.com.javacuriosities.jsf.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named("user")
@RequestScoped
public class UserBean {

    private String message = "Texto de prueba";
    private String nick;
    private String password;
    private String secretAnswer = "Respuesta secreta";
    private boolean rememberMe;
    private List<Integer> favouriteNumber;
    private String favouriteCoffe;

    public UserBean() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecretAnswer() {
        return secretAnswer;
    }

    public void setSecretAnswer(String secretAnswer) {
        this.secretAnswer = secretAnswer;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public List<Integer> getFavouriteNumber() {
        return favouriteNumber;
    }

    public void setFavouriteNumber(List<Integer> favouriteNumber) {
        this.favouriteNumber = favouriteNumber;
    }

    public String getFavouriteCoffe() {
        return favouriteCoffe;
    }

    public void setFavouriteCoffe(String favouriteCoffe) {
        this.favouriteCoffe = favouriteCoffe;
    }
}
