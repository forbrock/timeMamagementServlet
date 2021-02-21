package org.servlet.project.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.servlet.project.MainServlet;
import org.servlet.project.model.dto.UserActivityDto;
import org.servlet.project.model.entity.User;
import org.servlet.project.model.service.SecurityService;
import org.servlet.project.model.service.UserActivityService;
import org.servlet.project.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

import static org.servlet.project.util.ViewResolver.resolve;

public class IndexCommand implements Command {
    private static final Logger log = LogManager.getLogger(MainServlet.class);
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
        User user = securityService.getLoggedUser(session);

        // TODO: move validation to secure filter
        if (Objects.isNull(user)) {
            log.warn("Index controller: User not found, redirect to login page");
            session.invalidate();
            return "redirect:/login";
        }

        user = userService.findByEmail(securityService.getLoggedUser(session).getEmail()).get();
        List<UserActivityDto> userActivityDtos = userActivityService.findByUserId(user.getId());
        request.setAttribute("userActivities", userActivityDtos);
        return resolve("index");
    }
}
