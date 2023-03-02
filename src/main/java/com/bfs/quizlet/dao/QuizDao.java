package com.bfs.quizlet.dao;

import com.bfs.quizlet.domain.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class QuizDao {
    JdbcTemplate jdbcTemplate;
    QuizMapper mapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public QuizDao(JdbcTemplate jdbcTemplate, QuizMapper mapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Quiz> getAllQuiz() {
        String query = "SELECT * FROM Quiz ORDER BY end_time DESC";
        List<Quiz> quizzes = jdbcTemplate.query(query, mapper);
        return quizzes;
    }

    public Quiz getQuizByQuizId(int quiz_id) {
        String query = "SELECT * FROM Quiz WHERE quiz_id = ?";
        List<Quiz> quizzes = jdbcTemplate.query(query, mapper, quiz_id);
        return quizzes.get(0);
    }

    public List<Quiz> getQuizByUser(int user_id) {
        String query = "SELECT * FROM Quiz WHERE user_id = ?";
        List<Quiz> quizzes = jdbcTemplate.query(query, mapper, user_id);
        return quizzes;
    }

    public void storeQuizResult(int user_id, int category_id, String name,
                                Timestamp start_time, Timestamp end_time, int score, String detail) {
        String query = "INSERT INTO Quiz (user_id, category_id, name, start_time, end_time, score, detail) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, user_id, category_id, name, start_time, end_time, score, detail);
    }
}