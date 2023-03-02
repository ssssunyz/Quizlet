package com.bfs.quizlet.service;

import com.bfs.quizlet.dao.UserDao;
import com.bfs.quizlet.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void createNewUser(String firstname, String lastname, String email, String password, String phone, boolean is_active, boolean is_admin) {
        userDao.createNewUser(firstname, lastname, email, password, phone, is_active, is_admin);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public User getUserById(int id) {
        return userDao.getAllUsers().stream()
                .filter(a -> a.getUser_id() == id)
                .findFirst()
                // .orElse(new User(-1, "invalid username", "invalid password"));
                .orElse(null);
    }

    public Optional<User> validateLogin(String email, String password) {
        return userDao.getAllUsers().stream()
                .filter(u -> u.getEmail().equals(email)
                        && u.getPassword().equals(password))
                .findAny();
    }

    public Optional<User> userExist(String email) {
        return userDao.getAllUsers().stream()
                .filter(u -> u.getEmail().equals(email))
                .findAny();
    }

    public void changeUserStatus(int userId, int changedStatus) {
        userDao.changeUserStatus(userId, changedStatus);
    }
}
