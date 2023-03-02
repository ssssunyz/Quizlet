package com.bfs.quizlet.dao;

import com.bfs.quizlet.domain.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class FeedbackDao {
    JdbcTemplate jdbcTemplate;
    FeedbackMapper mapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public FeedbackDao(JdbcTemplate jdbcTemplate, FeedbackMapper mapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Feedback> getAllFeedbacks() {
        String query = "SELECT * FROM Feedback";
        List<Feedback> feedbacks = jdbcTemplate.query(query, mapper);
        return feedbacks;
    }

    public void storeFeedback(int user_id, String text, int rating, LocalDate date) {
        String query = "INSERT INTO Feedback (user_id, text, rating, date) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(query, user_id, text, rating, date);
    }
}