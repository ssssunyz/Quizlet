package com.bfs.quizlet.service;

import com.bfs.quizlet.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class LoginService {
    private final UserService userService;

    @Autowired
    public LoginService(UserService userService) {
        this.userService = userService;
    }

    public Optional<User> validateLogin(String email, String password) {
        return userService.validateLogin(email, password);
    }

    public Optional<User> userExist(String email) {
        return userService.userExist(email);
    }

    public void createNewUser(String firstname, String lastname, String email, String password, String phone, boolean is_active, boolean is_admin) {
        userService.createNewUser(firstname, lastname, email, password, phone, is_active, is_admin);
    }
}
