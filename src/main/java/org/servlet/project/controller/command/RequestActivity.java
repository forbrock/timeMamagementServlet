package org.servlet.project.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.servlet.project.exceptions.ActivityAlreadyExistException;
import org.servlet.project.model.service.SecurityService;
import org.servlet.project.model.service.UserActivityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

import static org.servlet.project.util.ViewResolver.resolve;

public class RequestActivity implements Command {
    private static final Logger log = LogManager.getLogger(RequestActivity.class);
    public static final String REDIRECT_TO_INDEX = "redirect:/index";

    private final UserActivityService userActivityService;
    private final SecurityService securityService;

    public RequestActivity(UserActivityService userActivityService, SecurityService securityService) {
        this.userActivityService = userActivityService;
        this.securityService = securityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter("activityId");
        HttpSession session = request.getSession();

        if (Objects.isNull(id)) {
            return resolve("index");
        }

        long activityId = Long.parseLong(id);
        long userId = securityService.getLoggedUser(request.getSession()).getId();

        try {
            userActivityService.createRequest(userId, activityId);
        } catch (ActivityAlreadyExistException e) {
            session.setAttribute("request_failure_message", true);
            return REDIRECT_TO_INDEX;
        }
        session.setAttribute("request_success_message", true);
        log.info("Activity requested successfully [user id: {}, activity id: {}]", userId, activityId);
        return REDIRECT_TO_INDEX;
    }
}
