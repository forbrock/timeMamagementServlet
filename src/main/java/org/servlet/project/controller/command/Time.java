package org.servlet.project.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.servlet.project.model.service.TimeLogService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static org.servlet.project.util.ViewResolver.resolve;

public class Time implements Command {
    private static final Logger log = LogManager.getLogger(Time.class);

    private final TimeLogService timeLogService;

    public Time(TimeLogService timeLogService) {
        this.timeLogService = timeLogService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String time = request.getParameter("time");
        String uaId = request.getParameter("uaId");

        if (Objects.isNull(time) || Objects.isNull(uaId)) {
            request.setAttribute("timeIsEmptyMessage", "valid.index.time.empty");
            return resolve("index");
        }

        timeLogService.addNewTimePoint(uaId, time);
        log.info("New time point saved [id: {}, time: {}]", uaId, time);
        return "redirect:/index";
    }
}
