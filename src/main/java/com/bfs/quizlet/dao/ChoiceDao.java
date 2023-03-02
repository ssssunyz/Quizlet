package com.bfs.quizlet.dao;

import com.bfs.quizlet.domain.Choice;
import com.bfs.quizlet.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ChoiceDao {
    JdbcTemplate jdbcTemplate;
    ChoiceMapper mapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public ChoiceDao(JdbcTemplate jdbcTemplate, ChoiceMapper mapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Choice> getAllChoices() {
        String query = "SELECT * FROM Choice";
        List<Choice> choices = jdbcTemplate.query(query, mapper);
        return choices;
    }

    public List<Choice> getChoicesByQuestion(int question_id) {
        String query = "SELECT * FROM Choice WHERE question_id = ?";
        List<Choice> choices = jdbcTemplate.query(query, mapper, question_id);
        return choices;
    }

    public Choice getChoiceById(int choice_id) {
        String query = "SELECT * FROM Choice WHERE choice_id = ?";
        List<Choice> choices = jdbcTemplate.query(query, mapper, choice_id);
        return choices.size() == 0? null : choices.get(0);
    }

    public int getQuestionIdByChoiceId(int choice_id) {
        String query = "SELECT * FROM Choice WHERE choice_id = ?";
        List<Choice> choices = jdbcTemplate.query(query, mapper, choice_id);
        return choices.get(0).getQuestion_id();
    }
}