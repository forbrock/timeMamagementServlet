package org.servlet.project.controller.command;

import org.servlet.project.exceptions.ActivityAlreadyExistException;
import org.servlet.project.model.entity.Activity;
import org.servlet.project.model.service.ActivityService;
import org.servlet.project.model.service.CategoryService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.Optional;

import static org.servlet.project.util.ViewResolver.resolveAdmin;

public class AdminActivityEdit implements Command {
    private final ActivityService activityService;
    private final CategoryService categoryService;

    public AdminActivityEdit(ActivityService activityService, CategoryService categoryService) {
        this.activityService = activityService;
        this.categoryService = categoryService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter("id");
        if (Objects.isNull(id) || id.isEmpty()) {
            request.getSession().setAttribute(
                    "activity_error_message", "modal.new.activity.not.found");
            return "redirect:/admin/activities";
        }

        Optional<Activity> activity = activityService.findById(Long.parseLong(id));
        if (activity.isEmpty()) {
            request.getSession().setAttribute(
                    "activity_error_message", "modal.new.activity.not.found");
            return "redirect:/admin/activities";
        }

        request.setAttribute("name", activity.get().getName());
        request.setAttribute("categories", categoryService.findAll());

        if (request.getMethod().equals("POST")) {
            String activityName = request.getParameter("name");
            if (Objects.isNull(activityName) || activityName.isEmpty()) {
                request.getSession().setAttribute(
                        "activity_error_message", "modal.new.activity.not.found");
                request.setAttribute("id", id);
                return "redirect:/admin/activities";
            }
            String categoryId = request.getParameter("category");

            try {
                activityService.update(Long.parseLong(id), activityName);
            } catch (ActivityAlreadyExistException e) {
                request.setAttribute("id", id);
                request.setAttribute("activity_exists_message", true);
                return resolveAdmin("admin_edit_activity");
            }
            return "redirect:/admin/activities";
        }

        request.setAttribute("id", id);
        return resolveAdmin("admin_edit_activity");
    }
}
