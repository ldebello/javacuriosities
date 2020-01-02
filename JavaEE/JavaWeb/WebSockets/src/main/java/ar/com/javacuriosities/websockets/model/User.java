package ar.com.javacuriosities.websockets.model;

public class User {

    private String name;
    private String pass;

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "{" + "name=" + name + ", pass=" + pass + '}';
    }
}
