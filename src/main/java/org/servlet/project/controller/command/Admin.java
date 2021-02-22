package org.servlet.project.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.servlet.project.model.dto.UserActivityDto;
import org.servlet.project.model.service.UserActivityService;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static org.servlet.project.util.ViewResolver.resolveAdmin;

public class Admin implements Command {
    private static final Logger log = LogManager.getLogger(Admin.class);

    private final UserActivityService userActivityService;

    public Admin(UserActivityService userActivityService) {
        this.userActivityService = userActivityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<UserActivityDto> activities = userActivityService.findAll();
        request.setAttribute("activities", activities);
        return resolveAdmin("admin");
    }
}
