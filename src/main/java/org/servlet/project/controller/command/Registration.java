package org.servlet.project.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.servlet.project.exceptions.UserAlreadyExistException;
import org.servlet.project.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

import static org.servlet.project.util.ViewResolver.resolve;

public class Registration implements Command {
    private static final Logger log = LogManager.getLogger(Registration.class);
    private final UserService userService;

    public Registration(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if (request.getMethod().equals("GET")) {
            return resolve("registration");
        }

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String matchingPassword = request.getParameter("matchingPassword");
        String role = "USER";

        // TODO: check fields for null or empty
        // TODO: check that fields are filled correct
        // TODO: if filled wrong - pass words back
        // TODO: check that passwords are match
        try {
            userService.create(firstName, lastName, email, password, role);
        } catch (UserAlreadyExistException e) {
            request.setAttribute("userAlreadyExistsMessage", true);
            getBackUserInput(request, firstName, lastName, email);
            return resolve("registration");
        }
        return "redirect:/login";
    }

    private void getBackUserInput(HttpServletRequest request,
                                 String firstName, String lastName, String email) {
        request.setAttribute("first_name", firstName);
        request.setAttribute("last_name", lastName);
        request.setAttribute("email", email);
    }
}
