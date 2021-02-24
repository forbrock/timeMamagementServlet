package org.servlet.project.controller.command;

import org.servlet.project.exceptions.UserAlreadyExistException;
import org.servlet.project.model.entity.Role;
import org.servlet.project.model.entity.User;
import org.servlet.project.model.service.UserService;
import org.servlet.project.util.FormHelper;

import javax.servlet.http.HttpServletRequest;
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
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String matchingPassword = request.getParameter("matchingPassword");
        String role = request.getParameter("role");

        // TODO: check constraint validation
        try {
            userService.update(id, firstName, lastName, email, password, role);
            throw new UserAlreadyExistException();
        } catch (UserAlreadyExistException e) {
            request.setAttribute("email_not_valid", "valid.login.email.not.unique");
            FormHelper.fillAdminUserEditForm(request, firstName, lastName, email, role);
//            request.setAttribute("roles", Role.values());
        }
        return "redirect:/admin/users";
    }
}
