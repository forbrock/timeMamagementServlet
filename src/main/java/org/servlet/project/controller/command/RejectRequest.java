package org.servlet.project.controller.command;

import org.servlet.project.model.service.UserActivityService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class RejectRequest implements Command {
    private final UserActivityService userActivityService;
    public static final String REDIRECT_TO_ADMIN = "redirect:/admin";

    public RejectRequest(UserActivityService userActivityService) {
        this.userActivityService = userActivityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter("id");

        if (Objects.isNull(id)) {
            return REDIRECT_TO_ADMIN;
        }

        long activityId = Long.parseLong(id);
        userActivityService.delete(activityId);
        return REDIRECT_TO_ADMIN;
    }
}
