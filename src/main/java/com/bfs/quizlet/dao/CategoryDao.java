package com.bfs.quizlet.dao;

import com.bfs.quizlet.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CategoryDao {
    JdbcTemplate jdbcTemplate;
    CategoryMapper mapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public CategoryDao(JdbcTemplate jdbcTemplate, CategoryMapper mapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Category> getAllCategory() {
        String query = "SELECT * FROM Category";
        List<Category> categories = jdbcTemplate.query(query, mapper);
        return categories;
    }
}