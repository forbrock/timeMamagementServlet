package org.servlet.project.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.servlet.project.exceptions.ActivityAlreadyExistException;
import org.servlet.project.model.service.SecurityService;
import org.servlet.project.model.service.UserActivityService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static org.servlet.project.util.ViewResolver.resolve;

public class RequestActivityCommand implements Command {
    private static final Logger log = LogManager.getLogger(RequestActivityCommand.class);

    private UserActivityService userActivityService;
    private SecurityService securityService;

    public RequestActivityCommand(UserActivityService userActivityService, SecurityService securityService) {
        this.userActivityService = userActivityService;
        this.securityService = securityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter("activityId");

        if (Objects.isNull(id)) {
            return resolve("index");
        }

        long activityId = Long.parseLong(id);
        long userId = securityService.getLoggedUser(request.getSession()).getId();

        // TODO: add validation message to the front
        try {
            userActivityService.createRequest(userId, activityId);
        } catch (ActivityAlreadyExistException e) {
            request.setAttribute("request_failure_message", true);
            return resolve("index");
        }
        request.setAttribute("request_success_message", true);
        return resolve("index");
    }
}
