package org.servlet.project.controller.command;

import org.servlet.project.exceptions.CategoryAlreadyExistException;
import org.servlet.project.model.entity.Category;
import org.servlet.project.model.service.CategoryService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.Optional;

import static org.servlet.project.util.ViewResolver.resolveAdmin;

public class AdminCategoryEdit implements Command {
    private final CategoryService categoryService;

    public AdminCategoryEdit(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter("id");

        if (Objects.isNull(id)) {
            request.setAttribute("category_not_found", true);
            return resolveAdmin("admin_categories");
        }
        Optional<Category> category = categoryService.findById(Long.parseLong(id));

        if (category.isEmpty()) {
            request.setAttribute("category_not_found", true);
            return resolveAdmin("admin_categories");
        }
        request.setAttribute("name", category.get().getName());

        if (request.getMethod().equals("POST")) {
            String name = request.getParameter("name");
            if (Objects.isNull(name) || name.isEmpty()) {
                request.setAttribute("category_empty_name", true);
                request.setAttribute("id", id);
                return resolveAdmin("admin_edit_category");
            }
            try {
                categoryService.update(Long.parseLong(id), name);
            } catch (CategoryAlreadyExistException e) {
                request.setAttribute("id", id);
                request.setAttribute("category_already_exist", true);
                return resolveAdmin("admin_edit_category");
            }
            return "redirect:/admin/categories";
        }
        request.setAttribute("id", id);
        return resolveAdmin("admin_edit_category");
    }
}
