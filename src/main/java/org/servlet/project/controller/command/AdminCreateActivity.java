package org.servlet.project.controller.command;

import org.servlet.project.exceptions.CategoryAlreadyExistException;
import org.servlet.project.model.service.ActivityService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class AdminCreateActivity implements Command {
    private final ActivityService activityService;
    public static final String REDIRECT_TO_ADMIN_ACTIVITIES = "redirect:/admin/activities";

    public AdminCreateActivity(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("name");
        if (Objects.isNull(name) || name.isEmpty()) {
            request.getSession().setAttribute(
                    "activity_error_message", "valid.admin.new.activity.empty");
            return REDIRECT_TO_ADMIN_ACTIVITIES;
        }

        String categoryId = request.getParameter("category");

        try {
            activityService.create(name, Long.parseLong(categoryId));
        } catch (CategoryAlreadyExistException e) {
            request.getSession().setAttribute(
                    "category_error_message", "valid.admin.new.category.exists");
        }
        return REDIRECT_TO_ADMIN_ACTIVITIES;
    }
}
