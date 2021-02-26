package org.servlet.project.model.dao.mapper;

import org.servlet.project.model.entity.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryMapper implements ObjectMapper<Category> {
    @Override
    public Category extract(ResultSet rs) throws SQLException {
        return Category.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .build();
    }

    @Override
    public List<Category> extractAll(ResultSet rs) throws SQLException {
        List<Category> list = new ArrayList<>();

        while (rs.next()) {
            Category category = extract(rs);
            list.add(category);
        }
        return list;
    }
}
