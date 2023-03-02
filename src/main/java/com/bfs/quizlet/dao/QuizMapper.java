package com.bfs.quizlet.dao;

import com.bfs.quizlet.domain.Quiz;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

@Component
public class QuizMapper implements RowMapper<Quiz> {

    @Override
    public Quiz mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Quiz(rs.getInt("quiz_id"), rs.getInt("user_id"),
                rs.getInt("category_id"), rs.getString("name"),
                rs.getTime("start_time"), rs.getTime("end_time"),
                rs.getInt("score"), rs.getString("detail"));
    }
}