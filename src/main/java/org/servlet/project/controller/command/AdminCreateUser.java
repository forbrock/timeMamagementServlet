package org.servlet.project.controller.command;

import org.servlet.project.exceptions.UserAlreadyExistException;
import org.servlet.project.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

import static org.servlet.project.util.ViewResolver.resolve;
import static org.servlet.project.util.ViewResolver.resolveAdmin;

public class AdminCreateUser implements Command {
    private final UserService userService;

    public AdminCreateUser(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        // TODO: check fields for null or empty
        // TODO: check that fields are filled correct
        // TODO: if filled wrong - pass words back
        // TODO: check that passwords are match
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String matchingPassword = request.getParameter("matchingPassword");

        try {
            userService.createUser(firstName, lastName, email, password);
        } catch (UserAlreadyExistException e) {
            request.setAttribute("userAlreadyExistsMessage", true);
            userService.getBackUserInput(request, firstName, lastName, email);
            return resolveAdmin("admin_users");
        }
        return resolveAdmin("admin_users");
    }
}
