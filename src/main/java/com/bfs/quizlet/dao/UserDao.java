package com.bfs.quizlet.dao;

import com.bfs.quizlet.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserDao {
    JdbcTemplate jdbcTemplate;
    UserMapper mapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate, UserMapper mapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void createNewUser(String firstname, String lastname, String email, String password, String phone, boolean is_active, boolean is_admin) {
        String query = "INSERT INTO User (firstname, lastname, email, password, phone, is_active, is_admin) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, firstname, lastname, email, password, phone, is_active, is_admin);
    }

    public List<User> getAllUsers() {
        String query = "SELECT * FROM User";
        List<User> users = jdbcTemplate.query(query, mapper);
        return users;
    }

    public void changeUserStatus(int userId, int changedStatus) {
        String query = "UPDATE User SET is_active = ? WHERE user_id = ?";
        jdbcTemplate.update(query, changedStatus, userId);
    }
}