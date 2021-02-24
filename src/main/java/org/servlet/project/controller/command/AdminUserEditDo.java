package org.servlet.project.controller.command;

import org.servlet.project.exceptions.UserAlreadyExistException;
import org.servlet.project.model.entity.Role;
import org.servlet.project.model.entity.User;
import org.servlet.project.model.service.UserService;
import org.servlet.project.util.FormHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static org.servlet.project.util.ViewResolver.resolveAdmin;

public class AdminUserEditDo implements Command {
    private final UserService userService;

    public AdminUserEditDo(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        // TODO: validate fields
        String id = request.getParameter("id");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        String isEnabled = request.getParameter("isEnabled");
        String matchingPassword = request.getParameter("matchingPassword");
        String role = request.getParameter("role");

        userService.update(id, firstName, lastName, password, role, isEnabled);
        FormHelper.fillAdminUserEditForm(request, firstName, lastName, role);
//            request.setAttribute("roles", Role.values());
        return "redirect:/admin/users";
    }
}
