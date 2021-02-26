package org.servlet.project.controller.command;

import org.servlet.project.model.service.CategoryService;

import javax.servlet.http.HttpServletRequest;

import static org.servlet.project.util.ViewResolver.resolveAdmin;

public class AdminCategories implements Command {
    private final CategoryService categoryService;

    public AdminCategories(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("categories", categoryService.findAll());
        return resolveAdmin("admin_categories");
    }
}
