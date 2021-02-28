package org.servlet.project.controller.command;

import org.servlet.project.model.service.CategoryService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Objects;

public class AdminCategoryDelete implements Command {
    private final CategoryService categoryService;
    public static final String REDIRECT_TO_ADMIN_CATEGORIES = "redirect:/admin/categories";

    public AdminCategoryDelete(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter("id");
        if (Objects.isNull(id)) {
            request.getSession().setAttribute(
                    "category_error_message", "valid.admin.category.not.found.message");
            return REDIRECT_TO_ADMIN_CATEGORIES;
        }

        try {
            categoryService.delete(Long.parseLong(id));
        } catch (SQLException e) {
            request.getSession().setAttribute(
                    "category_error_message", "valid.admin.category.not.deletable.message");
        }
        return REDIRECT_TO_ADMIN_CATEGORIES;
    }
}
