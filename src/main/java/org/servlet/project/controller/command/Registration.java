package org.servlet.project.controller.command;

import org.servlet.project.controller.validation.Validation;
import org.servlet.project.exceptions.UserAlreadyExistException;
import org.servlet.project.model.service.UserService;
import org.servlet.project.util.FormHelper;

import javax.servlet.http.HttpServletRequest;

import static org.servlet.project.util.ViewResolver.resolve;

public class Registration implements Command {
    private final UserService userService;
    public static final String REGISTRATION = "registration";

    public Registration(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if (request.getMethod().equals("GET")) {
            return resolve(REGISTRATION);
        }

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String matchingPassword = request.getParameter("matchingPassword");
        String role = "USER";

        Validation validate = new Validation();

        if (validate.isEmpty(firstName, lastName, email, password, matchingPassword)) {
            request.setAttribute("errors", validate.getErrors());
            FormHelper.fillUserCreationForm(request, firstName, lastName, email);
            return resolve(REGISTRATION);
        }

        if (!validate.isValidEmail(email)) {
            request.setAttribute("errors", validate.getErrors());
            FormHelper.fillUserCreationForm(request, firstName, lastName, email);
            return resolve(REGISTRATION);
        }

        if (!validate.isPasswordsMatch(password, matchingPassword)) {
            request.setAttribute("errors", validate.getErrors());
            FormHelper.fillUserCreationForm(request, firstName, lastName, email);
            return resolve(REGISTRATION);
        }

        try {
            userService.create(firstName, lastName, email, password, role);
        } catch (UserAlreadyExistException e) {
            request.setAttribute("userAlreadyExistsMessage", true);
            FormHelper.fillUserCreationForm(request, firstName, lastName, email);
            return resolve(REGISTRATION);
        }
        return "redirect:/login";
    }
}
