package org.servlet.project.controller.command;

import org.servlet.project.model.service.UserService;
import org.servlet.project.util.FormHelper;

import javax.servlet.http.HttpServletRequest;

public class AdminUserEditDo implements Command {
    private final UserService userService;

    public AdminUserEditDo(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter("id");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        String isEnabled = request.getParameter("isEnabled");
        String matchingPassword = request.getParameter("matchingPassword");
        String role = request.getParameter("role");

        userService.update(id, firstName, lastName, password, role, isEnabled);
        FormHelper.fillAdminUserEditForm(request, firstName, lastName, role);
        return "redirect:/admin/users";
    }
}
