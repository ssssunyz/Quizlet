package com.bfs.quizlet.dao;

import com.bfs.quizlet.domain.QuizQuestionChoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class QuizQuestionChoiceDao {
    JdbcTemplate jdbcTemplate;
    QuizQuestionChoiceMapper mapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public QuizQuestionChoiceDao(JdbcTemplate jdbcTemplate, QuizQuestionChoiceMapper mapper,
                                 NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<QuizQuestionChoice> getQQCByQuizId(int quiz_id) {
        String query = "SELECT * FROM QuizQuestionChoice WHERE quiz_id = ?";
        List<QuizQuestionChoice> qqc = jdbcTemplate.query(query, mapper, quiz_id);
        return qqc;
    }
}