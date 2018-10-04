package ar.com.javacuriosities.springboot.service;

import ar.com.javacuriosities.springboot.model.User;

public interface UserService {
    User findUserByEmail(String email);

    void saveUser(User user);
}