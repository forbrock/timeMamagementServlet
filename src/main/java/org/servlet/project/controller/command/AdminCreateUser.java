package org.servlet.project.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.servlet.project.exceptions.UserAlreadyExistException;
import org.servlet.project.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AdminCreateUser implements Command {
    private static final Logger log = LogManager.getLogger(AdminCreateUser.class);
    private final UserService userService;

    public AdminCreateUser(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        // TODO: check fields for null or empty
        // TODO: check that fields are filled correct
        // TODO: if filled wrong - pass words back
        // TODO: check that passwords are match
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String matchingPassword = request.getParameter("matchingPassword");
        String role = request.getParameter("role");

        try {
            userService.create(firstName, lastName, email, password, role);
        } catch (UserAlreadyExistException e) {
            session.setAttribute("userAlreadyExistsMessage", true);
            return "redirect:/admin/users";
        }
        session.setAttribute("userCreatedMessage", true);
        if ("ADMIN".equals(role)) {
            log.info("User with ADMIN rights created [email: {}]", email);
        }
        return "redirect:/admin/users";
    }
}
