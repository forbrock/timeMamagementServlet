package org.servlet.project.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.servlet.project.exceptions.CategoryAlreadyExistException;
import org.servlet.project.model.dao.CategoryDao;
import org.servlet.project.model.dao.mapper.CategoryMapper;
import org.servlet.project.model.entity.Category;
import org.servlet.project.util.DBQueries;

import java.sql.*;
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
    public Optional<Category> update(Category category) {
        try (PreparedStatement statement =
                connection.prepareStatement(DBQueries.UPDATE_CATEGORY_QUERY)) {
            statement.setString(1, category.getName());
            statement.setLong(2, category.getId());
            boolean isUpdated = statement.executeUpdate() > 0;
            if (isUpdated) {
                return Optional.of(category);
            }
        } catch (SQLIntegrityConstraintViolationException ex1) {
            throw new CategoryAlreadyExistException();
        } catch (SQLException ex2) {
            log.error("Can not provide category update operation", ex2);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Category> findById(long id) {
        try (PreparedStatement statement =
                connection.prepareStatement(DBQueries.FIND_CATEGORY_BY_ID_QUERY)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return Optional.ofNullable(categoryMapper.extract(rs));
            }
        } catch (SQLException e) {
            log.error("Can not provide category findById operation", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Category> save(Category category) {
        try (PreparedStatement statement =
                connection.prepareStatement(DBQueries.SAVE_CATEGORY_QUERY)) {
            statement.setString(1, category.getName());
            boolean saved = statement.executeUpdate() > 0;
            if (saved) {
                return Optional.of(category);
            }
        } catch (SQLIntegrityConstraintViolationException ex1) {
            log.info("Attempt to create an existing category: {}", category.getName());
            throw new CategoryAlreadyExistException();
        } catch (SQLException ex2) {
            log.error("Can not provide category save operation", ex2);
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteById(long id) {
        try (PreparedStatement statement =
                connection.prepareStatement(DBQueries.DELETE_CATEGORY_BY_ID_QUERY)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            log.error("Can not provide category deleteById operation", e);
        }
        return false;
    }

    @Override
    public Optional<Category> delete(Category category) {
        return Optional.empty();
    }
}
