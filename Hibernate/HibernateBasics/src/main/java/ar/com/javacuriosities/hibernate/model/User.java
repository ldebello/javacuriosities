package ar.com.javacuriosities.hibernate.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User extends PersistentEntity {

    private String name;

    private String password;

    public User() {
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
}
