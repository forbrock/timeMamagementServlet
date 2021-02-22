package org.servlet.project.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.servlet.project.model.service.TimeLogService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static org.servlet.project.util.ViewResolver.resolve;

public class TimeCommand implements Command {
    private static final Logger log = LogManager.getLogger(TimeCommand.class);

    private final TimeLogService timeLogService;

    public TimeCommand(TimeLogService timeLogService) {
        this.timeLogService = timeLogService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String time = request.getParameter("time");
        String uaId = request.getParameter("uaId");

        // TODO: add validation message to the front
        if (Objects.isNull(time) || Objects.isNull(uaId)) {
            request.setAttribute("timeIsEmptyMessage", "valid.index.time.empty");
            return resolve("index");
        }

        timeLogService.addNewTimePoint(uaId, time);
        log.info("New time point saved, id: {}, time: {}", uaId, time);
        return "redirect:/index";
    }
}
