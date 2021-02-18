package org.servlet.project.controller.command;

import org.servlet.project.exception.UserNotFoundException;
import org.servlet.project.model.entity.User;
import org.servlet.project.model.service.SecurityService;
import org.servlet.project.model.service.UserActivityService;
import org.servlet.project.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class IndexCommand implements Command {
    private UserService userService;
    private UserActivityService userActivityService;
    private SecurityService securityService;

    public IndexCommand(UserService userService,
                        UserActivityService userActivityService,
                        SecurityService securityService) {
        this.userService = userService;
        this.userActivityService = userActivityService;
        this.securityService = securityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = userService.findByEmail(
                securityService.getLoggedUser(session).getEmail()).orElseThrow(() ->
                new UserNotFoundException("User not found"));
        request.setAttribute("userActivities", userActivityService.findByUserId(user.getId()));
        return "/WEB-INF/view/index.jsp";
    }
}
