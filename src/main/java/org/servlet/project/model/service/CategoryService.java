package org.servlet.project.model.service;

import org.servlet.project.model.dao.CategoryDao;
import org.servlet.project.model.dao.impl.DaoFactory;
import org.servlet.project.model.entity.Category;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CategoryService {
    private final CategoryDao categoryDao = DaoFactory.createCategoryDao();

    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    public Optional<Category> findById(long id) {
        return categoryDao.findById(id);
    }

    public Optional<Category> update(long id, String name) {
        return categoryDao.update(Category.builder()
                .id(id)
                .name(name)
                .build());
    }

    public Optional<Category> create(String name) {
        return categoryDao.save(Category.builder()
                .name(name)
                .build());
    }

    public boolean delete(long id) throws SQLException {
        return categoryDao.deleteById(id);
    }
}
