package org.servlet.project.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.servlet.project.MainServlet;
import org.servlet.project.model.entity.User;
import org.servlet.project.model.service.SecurityService;
import org.servlet.project.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.Optional;

import static org.servlet.project.util.ViewResolver.resolve;

public class LoginCommand implements Command {
    private static final Logger log = LogManager.getLogger(LoginCommand.class);

    private final UserService userService;
    private final SecurityService securityService;

    public LoginCommand(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if (request.getMethod().equals("GET")) {
            return resolve("login");
        }
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String goToLogin = "/WEB-INF/view/login.jsp";

        // TODO: create a separate validator for these cases
        // TODO: add fields validation
        if(Objects.isNull(email) || Objects.isNull(password) ||
                email.isEmpty() || password.isBlank()) {
            return goToLogin;
        }

        // TODO: check validation message
        Optional<User> user = userService.findByEmail(email);
        if (user.isEmpty()) {
            request.setAttribute("loginFailureMessage", "valid.login.login.failure");
            return goToLogin;
        }

        // TODO: check validation message
        if (securityService.isLogged(request, email)) {
            request.setAttribute("userAlreadyLoggedMessage", "valid.login.already.logged.in");
            return goToLogin;
        }

        // TODO: check validation message
        if (!securityService.passwordIsValid(password, user.get())) {
            request.setAttribute("passFailureMessage", "valid.login.password.failure");
            return goToLogin;
        }

        securityService.storeLoggedUser(request.getSession(), user.get());

        return "redirect:/index";
    }
}
