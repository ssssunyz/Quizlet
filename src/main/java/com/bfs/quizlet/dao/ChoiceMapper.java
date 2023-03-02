package com.bfs.quizlet.dao;

import com.bfs.quizlet.domain.Choice;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

@Component
public class ChoiceMapper implements RowMapper<Choice> {

    @Override
    public Choice mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Choice(rs.getInt("choice_id"), rs.getInt("question_id"),
                rs.getString("choice_description"), rs.getBoolean("is_correct"));
    }
}