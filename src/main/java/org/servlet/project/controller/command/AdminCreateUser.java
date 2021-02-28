package org.servlet.project.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.servlet.project.controller.validation.Validation;
import org.servlet.project.exceptions.UserAlreadyExistException;
import org.servlet.project.model.service.UserService;
import org.servlet.project.util.FormHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AdminCreateUser implements Command {
    private static final Logger log = LogManager.getLogger(AdminCreateUser.class);
    private final UserService userService;
    public static final String REDIRECT_TO_ADMIN_USERS = "redirect:/admin/users";

    public AdminCreateUser(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String matchingPassword = request.getParameter("matchingPassword");
        String role = request.getParameter("role");

        Validation validate = new Validation();

        if (validate.isEmpty(firstName, lastName, email, password, matchingPassword)) {
            session.setAttribute("errors", validate.getErrors());
            FormHelper.fillUserCreationForm(request, firstName, lastName, email);
            return REDIRECT_TO_ADMIN_USERS;
        }

        if (!validate.isValidEmail(email)) {
            session.setAttribute("errors", validate.getErrors());
            FormHelper.fillUserCreationForm(request, firstName, lastName, email);
            return REDIRECT_TO_ADMIN_USERS;
        }

        if (!validate.isPasswordsMatch(password, matchingPassword)) {
            session.setAttribute("errors", validate.getErrors());
            FormHelper.fillUserCreationForm(request, firstName, lastName, email);
            return REDIRECT_TO_ADMIN_USERS;
        }

        try {
            userService.create(firstName, lastName, email, password, role);
        } catch (UserAlreadyExistException e) {
            session.setAttribute("userAlreadyExistsMessage", true);
            return REDIRECT_TO_ADMIN_USERS;
        }
        session.setAttribute("userCreatedMessage", true);
        if ("ADMIN".equals(role)) {
            log.info("User with ADMIN rights created [email: {}]", email);
        }
        return REDIRECT_TO_ADMIN_USERS;
    }
}
