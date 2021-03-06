package org.servlet.project.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.servlet.project.model.dto.UserActivityDto;
import org.servlet.project.model.entity.Activity;
import org.servlet.project.model.entity.User;
import org.servlet.project.model.service.ActivityService;
import org.servlet.project.model.service.SecurityService;
import org.servlet.project.model.service.UserActivityService;
import org.servlet.project.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

import static org.servlet.project.util.ViewResolver.resolve;

public class Index implements Command {
    private static final Logger log = LogManager.getLogger(Index.class);
    private final UserService userService;
    private final UserActivityService userActivityService;
    private final SecurityService securityService;
    private final ActivityService activityService;

    public Index(UserService userService,
                 UserActivityService userActivityService,
                 SecurityService securityService, ActivityService activityService) {
        this.userService = userService;
        this.userActivityService = userActivityService;
        this.securityService = securityService;
        this.activityService = activityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = securityService.getLoggedUser(session);

        if (Objects.isNull(user)) {
            log.debug("Index controller: User not found, redirect to login page");
            session.invalidate();
            return "redirect:/login";
        }

        user = userService.findByEmail(securityService.getLoggedUser(session).getEmail()).get();
        List<UserActivityDto> userActivityDtos = userActivityService.findByUserId(user.getId());
        List<Activity> activities = activityService.findAll();

        request.setAttribute("userActivities", userActivityDtos);
        request.setAttribute("activities", activities);
        return resolve("index");
    }
}
