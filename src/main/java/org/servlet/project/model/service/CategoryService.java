package org.servlet.project.model.service;

import org.servlet.project.model.dao.CategoryDao;
import org.servlet.project.model.dao.impl.DaoFactory;
import org.servlet.project.model.entity.Category;

import java.util.List;

public class CategoryService {
    private final CategoryDao categoryDao = DaoFactory.createCategoryDao();

    public List<Category> findAll() {
        return categoryDao.findAll();
    }
}
