package org.servlet.project.controller.command;

import org.servlet.project.model.entity.Role;
import org.servlet.project.model.entity.User;
import org.servlet.project.model.service.UserService;
import org.servlet.project.util.FormHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static org.servlet.project.util.ViewResolver.resolveAdmin;

public class AdminUserEdit implements Command {
    private final UserService userService;

    public AdminUserEdit(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter("id");
        Optional<User> user = userService.findById(Long.parseLong(id));

        // TODO: add validation message to the front
        if (user.isEmpty()) {
            request.setAttribute("UserNotFound", true);
            return resolveAdmin("admin_user_edit");
        }

        FormHelper.fillAdminUserEditForm(request, user.get().getFirstName(),
                user.get().getLastName(), user.get().getEmail(), user.get().getRole().name());
        request.setAttribute("roles", Role.values());
        request.setAttribute("id", id);

        return resolveAdmin("admin_user_edit");
    }
}
