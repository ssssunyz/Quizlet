package com.bfs.quizlet.dao;

import com.bfs.quizlet.domain.User;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

@Component
public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(rs.getInt("user_id"), rs.getString("firstname"),
                rs.getString("lastname"), rs.getString("email"),
                rs.getString("password"), rs.getString("phone"),
                rs.getBoolean("is_active"), rs.getBoolean("is_admin"));
    }
}