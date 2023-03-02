package com.bfs.quizlet.dao;

import com.bfs.quizlet.domain.Category;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

@Component
public class CategoryMapper implements RowMapper<Category> {

    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Category(rs.getInt("category_id"), rs.getString("name"),
                rs.getString("image_url"));
    }
}