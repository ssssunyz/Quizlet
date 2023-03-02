package com.bfs.quizlet.dao;

import com.bfs.quizlet.domain.QuizQuestionChoice;
import com.bfs.quizlet.domain.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class QuizQuestionChoiceMapper implements RowMapper<QuizQuestionChoice> {

    @Override
    public QuizQuestionChoice mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new QuizQuestionChoice(rs.getInt("qq_id"), rs.getInt("quiz_id"),
                rs.getInt("question_id"), rs.getInt("choice_id"),
                rs.getBoolean("is_selected"), rs.getBoolean("is_correct"));
    }
}