package org.servlet.project.model.service;

import org.mindrot.jbcrypt.BCrypt;
import org.servlet.project.model.entity.Role;
import org.servlet.project.model.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.Set;

public class SecurityService {

    public User getLoggedUser(HttpSession session) {
        return (User) session.getAttribute("loggedUser");
    }

    @SuppressWarnings("unchecked")
    public boolean isLogged(HttpServletRequest request, String email) {
        Set<String> loggedUsers = (Set<String>) request
                .getSession()
                .getServletContext()
                .getAttribute("loggedUsers");

        if (Objects.nonNull(loggedUsers) && loggedUsers.stream().anyMatch(email::equals)) {
            return true;
        }

        ServletContext context = request.getServletContext();
        context.setAttribute("email", email);
        request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
        return false;
    }

    public void storeLoggedUser(HttpSession session, User user) {
        session.setAttribute("loggedUser", user);
    }

    public String getRedirectUri(HttpServletRequest request) {
        User user = getLoggedUser(request.getSession());
        if (user.getRole() == Role.ADMIN) {
            return request.getContextPath() + "/admin/admin";
        }
        return request.getContextPath() + "/index";
    }

    public boolean passwordIsValid(String candidate, User user) {
        return BCrypt.checkpw(candidate, user.getPassword());
    }

    public String encrypt(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }
}
