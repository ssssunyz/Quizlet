package com.bfs.quizlet.dao;

import com.bfs.quizlet.domain.Feedback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

@Component
public class FeedbackMapper implements RowMapper<Feedback> {

    @Override
    public Feedback mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Feedback(rs.getInt("feedback_id"), rs.getInt("user_id"),
                rs.getString("text"), rs.getInt("rating"), rs.getDate("date"));
    }
}