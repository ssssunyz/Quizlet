package com.bfs.quizlet.dao;

import com.bfs.quizlet.domain.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ContactDao {
    JdbcTemplate jdbcTemplate;
    ContactMapper mapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public ContactDao(JdbcTemplate jdbcTemplate, ContactMapper mapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Contact> getAllContacts() {
        String query = "SELECT * FROM Contact";
        List<Contact> contacts = jdbcTemplate.query(query, mapper);
        return contacts;
    }

    public void storeContactMsg(int user_id, String subject, String msg) {
        String query = "INSERT INTO Contact (user_id, subject, message) VALUES (?, ?, ?)";
        jdbcTemplate.update(query, user_id, subject, msg);
    }
}
