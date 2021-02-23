package org.servlet.project.controller.command;

import org.servlet.project.model.entity.Role;
import org.servlet.project.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

import static org.servlet.project.util.ViewResolver.resolveAdmin;

public class AdminUsers implements Command {
    private final UserService userService;

    public AdminUsers(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("users", userService.findAll());
        request.setAttribute("roles", Role.values());
        return resolveAdmin("admin_users");
    }
}
