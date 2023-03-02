package com.bfs.quizlet.dao;

import com.bfs.quizlet.domain.Question;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

@Component
public class QuestionMapper implements RowMapper<Question> {

    @Override
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Question(rs.getInt("question_id"), rs.getInt("category_id"),
                rs.getString("description"), rs.getBoolean("is_active"),
                rs.getInt("correctChoiceId"));
    }
}