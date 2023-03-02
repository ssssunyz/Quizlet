package com.bfs.quizlet.dao;

import com.bfs.quizlet.domain.Contact;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ContactMapper implements RowMapper<Contact> {

    @Override
    public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Contact(rs.getInt("contact_id"), rs.getInt("user_id"),
                rs.getString("subject"), rs.getString("message"));
    }
}