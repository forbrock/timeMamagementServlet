package org.servlet.project.controller.command;

import org.servlet.project.model.service.UserActivityService;

import javax.servlet.http.HttpServletRequest;

import static org.servlet.project.util.ViewResolver.resolveAdmin;

public class Admin implements Command {
    private final UserActivityService userActivityService;

    public Admin(UserActivityService userActivityService) {
        this.userActivityService = userActivityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("activities", userActivityService.findAll());
        return resolveAdmin("admin");
    }
}
