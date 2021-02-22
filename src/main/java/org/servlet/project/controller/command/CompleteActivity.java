package org.servlet.project.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.servlet.project.model.service.UserActivityService;

import javax.servlet.http.HttpServletRequest;

public class CompleteActivity implements Command {
    private static final Logger log = LogManager.getLogger(CompleteActivity.class);
    private final UserActivityService userActivityService;

    public CompleteActivity(UserActivityService userActivityService) {
        this.userActivityService = userActivityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        long id = Long.parseLong(request.getParameter("id"));
        userActivityService.completeTask(id);
        log.info("Activity with id {} completed", id);
        return "redirect:/index";
    }
}
