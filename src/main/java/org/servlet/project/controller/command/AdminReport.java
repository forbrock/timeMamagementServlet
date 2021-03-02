package org.servlet.project.controller.command;

import org.servlet.project.model.dto.UserActivityDto;
import org.servlet.project.model.service.UserActivityService;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static org.servlet.project.util.ViewResolver.resolveAdmin;

public class AdminReport implements Command {
    private final UserActivityService userActivityService;

    public AdminReport(UserActivityService userActivityService) {
        this.userActivityService = userActivityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<UserActivityDto> activities = userActivityService.findAll();
        String sortOrder = "desc";
        request.setAttribute("sortOrder", sortOrder);
        sortActivities(request, activities);
        request.setAttribute("ua_report", activities);
        return resolveAdmin("admin_report");
    }

    private void sortActivities(HttpServletRequest request, List<UserActivityDto> activities) {
        String sortBy = request.getParameter("sortBy");
        String order = request.getParameter("order");
        String sortOrder = "";
        if (order != null) {
            sortOrder = order.equals("asc") ? "desc" : "asc";
            request.setAttribute("sortOrder", sortOrder);
        }

        Comparator<UserActivityDto> comparator = Comparator.comparing(UserActivityDto::getEmail);
        if (sortBy != null) {
            switch (sortBy) {
                case "user":
                    comparator = Comparator.comparing(UserActivityDto::getEmail);
                    break;
                case "activity":
                    comparator = Comparator.comparing(UserActivityDto::getActivity);
                    break;
                case "category":
                    comparator = Comparator.comparing(UserActivityDto::getCategory);
                    break;
                case "state":
                    comparator = Comparator.comparing(ua -> ua.getState().toString());
                    break;
                case "time":
                    comparator = Comparator.comparing(UserActivityDto::getDuration);
                    break;
                default:
                    break;
            }
        }
        if (sortOrder.equals("desc")) {
            activities.sort(comparator.reversed());
            return;
        }
        activities.sort(comparator);
    }
}
