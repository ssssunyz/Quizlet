package com.bfs.quizlet.dao;

import com.bfs.quizlet.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class QuestionDao {
    JdbcTemplate jdbcTemplate;
    QuestionMapper mapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public QuestionDao(JdbcTemplate jdbcTemplate, QuestionMapper mapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void addQuestion(int categoryId, String description) {
        String query = "INSERT INTO Question (category_id, description) VALUES (?, ?)";
        jdbcTemplate.update(query, categoryId, description);
    }

    public List<Question> getAllQuestionByCategory(int category_id) {
        String query = "SELECT * FROM Question WHERE category_id = ?";
        List<Question> all_questions = jdbcTemplate.query(query, mapper, category_id);
        return all_questions;
    }

    public List<Question> getFiveQuestionByCategory(int category_id) {
        String query = "SELECT * FROM Question WHERE category_id = ? AND is_active = 1";
        List<Question> all_questions = jdbcTemplate.query(query, mapper, category_id);
        Set<Question> questions = new HashSet<>();
        while (questions.size() < 5)  // randomly select 5 UNIQUE questions
            questions.add(all_questions.get(new Random().nextInt(all_questions.size())));

        // questions.forEach(q -> System.out.println(q));
        return new ArrayList<>(questions);
    }

    public Question getQuestionById(int question_id) {
        String query = "SELECT * FROM Question WHERE question_id = ?";
        List<Question> questions = jdbcTemplate.query(query, mapper, question_id);
        return questions.get(0);
    }

    public void changeQuestionStatus(int questionId, int changedStatus) {
        String query = "UPDATE Question SET is_active = ? WHERE question_id = ?";
        jdbcTemplate.update(query, changedStatus, questionId);
    }

    public void updateQuestionById(int question_id, int category_id, String description) {
        String query = "UPDATE Question SET category_id = ?, description = ? WHERE question_id = ?";
        jdbcTemplate.update(query, category_id, description, question_id);
    }
}