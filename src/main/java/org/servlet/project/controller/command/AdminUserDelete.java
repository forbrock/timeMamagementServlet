package org.servlet.project.controller.command;

import org.servlet.project.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static org.servlet.project.util.ViewResolver.resolveAdmin;

public class AdminUserDelete implements Command {
    private final UserService userService;

    public AdminUserDelete(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter("id");
        if (Objects.isNull(id)) {
            request.setAttribute("user_not_found_message", true);
            return resolveAdmin("admin_users");
        }
        userService.delete(Long.parseLong(id));
        return "redirect:/admin/users";
    }
}
