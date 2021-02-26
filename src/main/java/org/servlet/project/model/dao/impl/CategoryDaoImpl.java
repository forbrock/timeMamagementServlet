package org.servlet.project.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.servlet.project.model.dao.CategoryDao;
import org.servlet.project.model.dao.mapper.CategoryMapper;
import org.servlet.project.model.entity.Category;
import org.servlet.project.util.DBQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryDaoImpl implements CategoryDao {
    private static final Logger log = LogManager.getLogger(CategoryDaoImpl.class);
    private final CategoryMapper categoryMapper = new CategoryMapper();
    private Connection connection;

    public CategoryDaoImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<Category> findAll() {
        try (PreparedStatement statement =
                connection.prepareStatement(DBQueries.FIND_ALL_CATEGORIES_QUERY)) {
            ResultSet rs = statement.executeQuery();
            return categoryMapper.extractAll(rs);
        } catch (SQLException e) {
            log.error("Can not provide findAll operation", e);
        }
        return new ArrayList<>();
    }

    @Override
    public Optional<Category> findById(long id) {
        return Optional.empty();
    }

    @Override
    public Category save(Category category) {
        return null;
    }

    @Override
    public Category update(Category category) {
        return null;
    }

    @Override
    public Category delete(Category category) {
        return null;
    }
}
