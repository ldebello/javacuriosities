package ar.com.javacuriosities.annotations.model;

import ar.com.javacuriosities.annotations.FieldMapping;
import ar.com.javacuriosities.annotations.Table;

@Table(tableName = "users")
public class User {

    @FieldMapping(name = "id", isPk = true)
    private Integer id;

    @FieldMapping(name = "name")
    private String name;

    @FieldMapping(name = "age")
    private Integer age;

    @FieldMapping(name = "administrator")
    private Boolean isAdmin;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}