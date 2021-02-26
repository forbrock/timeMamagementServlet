package org.servlet.project.controller.command;

import org.servlet.project.model.dto.UserActivityDto;
import org.servlet.project.model.service.UserActivityService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

import static org.servlet.project.util.ViewResolver.resolveAdmin;

public class AdminUserActivities implements Command {
    private final UserActivityService uaService;

    public AdminUserActivities(UserActivityService uaService) {
        this.uaService = uaService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter("id");
        if (Objects.isNull(id)) {
            request.setAttribute("user_not_found_message", true);
            return "redirect:/admin/users";
        }
        List<UserActivityDto> activities = uaService.findByUserId(Long.parseLong(id));
        request.setAttribute("activities", activities);
        if (activities.size() > 0) {
            request.setAttribute("email", activities.get(0).getEmail());
        }
        return resolveAdmin("admin_user_activities");
    }
}
