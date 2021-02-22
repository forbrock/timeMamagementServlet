package org.servlet.project.controller.command;

import org.servlet.project.model.service.UserActivityService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static org.servlet.project.util.ViewResolver.resolveAdmin;

public class AdminAcceptActivity implements Command {
    private final UserActivityService userActivityService;

    public AdminAcceptActivity(UserActivityService userActivityService) {
        this.userActivityService = userActivityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter("id");

        if (Objects.isNull(id)) {
            return "redirect:/admin";
        }

        long activityId = Long.parseLong(id);
        userActivityService.acceptActivity(activityId);
        return "redirect:/admin";
    }
}
