package org.servlet.project.controller.command;

import org.servlet.project.model.entity.User;
import org.servlet.project.model.service.SecurityService;
import org.servlet.project.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.Optional;

public class LoginCommand implements Command {
    private final UserService userService;
    private final SecurityService securityService;

    public LoginCommand(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String goToLogin = "/WEB-INF/view/login.jsp";

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

//        request.getSession().invalidate();
        securityService.storeLoggedUser(request.getSession(), user.get());

        // TODO: implement getting uri
        String uri = securityService.getRedirectUri(request);

        if (Objects.nonNull(uri)) {
            return "redirect:" + uri;
        }
        return "redirect:index";
    }
}
