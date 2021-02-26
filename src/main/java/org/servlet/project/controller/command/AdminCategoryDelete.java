package org.servlet.project.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.servlet.project.model.service.CategoryService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Objects;

import static org.servlet.project.util.ViewResolver.resolveAdmin;

public class AdminCategoryDelete implements Command {
    private static final Logger log = LogManager.getLogger(AdminCategoryDelete.class);
    private final CategoryService categoryService;

    public AdminCategoryDelete(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter("id");
        if (Objects.isNull(id)) {
            request.getSession().setAttribute(
                    "category_error_message", "valid.admin.category.not.found.message");
            return "redirect:/admin/categories";
        }

        try {
            categoryService.delete(Long.parseLong(id));
        } catch (SQLException e) {
            request.getSession().setAttribute(
                    "category_error_message", "valid.admin.category.not.deletable.message");
        }
        return "redirect:/admin/categories";
    }
}
