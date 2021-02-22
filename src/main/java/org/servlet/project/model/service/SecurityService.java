package org.servlet.project.model.service;

import org.mindrot.jbcrypt.BCrypt;
import org.servlet.project.model.entity.Role;
import org.servlet.project.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.servlet.project.util.ViewResolver.resolveAdmin;

public class SecurityService {

    public User getLoggedUser(HttpSession session) {
        return (User) session.getAttribute("loggedUser");
    }

    public void storeLoggedUser(HttpSession session, User user) {
        session.setAttribute("loggedUser", user);
    }

    public String setUriByRole(HttpServletRequest request) {
        User user = getLoggedUser(request.getSession());
        if (user.getRole() == Role.ADMIN) {
            return "/admin";
        }
        return "/index";
    }

    public boolean passwordIsValid(String candidate, User user) {
        return BCrypt.checkpw(candidate, user.getPassword());
    }

    public String encrypt(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }
}
