package org.servlet.project.controller.command;

import org.servlet.project.model.service.ActivityService;
import org.servlet.project.model.service.CategoryService;

import javax.servlet.http.HttpServletRequest;

import static org.servlet.project.util.ViewResolver.resolveAdmin;

public class AdminActivities implements Command {
    private final ActivityService activityService;
    private final CategoryService categoryService;

    public AdminActivities(ActivityService activityService, CategoryService categoryService) {
        this.activityService = activityService;
        this.categoryService = categoryService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("categories", categoryService.findAll());
        request.setAttribute("activities", activityService.findAll());
        return resolveAdmin("admin_activities");
    }
}
