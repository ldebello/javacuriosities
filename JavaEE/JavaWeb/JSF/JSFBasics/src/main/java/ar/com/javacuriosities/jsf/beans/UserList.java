package ar.com.javacuriosities.jsf.beans;

import java.io.Serializable;
import java.util.List;

public class UserList implements Serializable {

    private List<String> users;
    private String selectedUser;

    public UserList() {
    }

    public void delete() {
        users.remove(selectedUser);
    }

    public String getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(String selectedUser) {
        this.selectedUser = selectedUser;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }
}
