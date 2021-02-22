package org.servlet.project.controller.command;

import org.servlet.project.model.service.UserActivityService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class RejectRequest implements Command {
    private final UserActivityService userActivityService;

    public RejectRequest(UserActivityService userActivityService) {
        this.userActivityService = userActivityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter("id");

        if (Objects.isNull(id)) {
            return "redirect:/admin";
        }

        long activityId = Long.parseLong(id);
        userActivityService.rejectRequest(activityId);
        return "redirect:/admin";
    }
}
