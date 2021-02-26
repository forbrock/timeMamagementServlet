package org.servlet.project.controller.command;

import org.servlet.project.exceptions.CategoryAlreadyExistException;
import org.servlet.project.model.service.CategoryService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class AdminCreateCategory implements Command {
    private final CategoryService categoryService;

    public AdminCreateCategory(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("name");
        if (Objects.isNull(name) || name.isEmpty()) {
            request.getSession().setAttribute(
                    "category_error_message", "valid.admin.new.category.empty");
            return "redirect:/admin/categories";
        }
        try {
            categoryService.create(name);
        } catch (CategoryAlreadyExistException e) {
            request.getSession().setAttribute(
                    "category_error_message", "valid.admin.new.category.exists");
        }
        return "redirect:/admin/categories";
    }
}
