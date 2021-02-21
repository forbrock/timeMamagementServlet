package org.servlet.project.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class TimeCommand implements Command {
    private static final Logger log = LogManager.getLogger(TimeCommand.class);
    @Override
    public String execute(HttpServletRequest request) {
        log.info("time: {}, id: {}",
                request.getParameter("time"),
                request.getParameter("uaId"));

        return "redirect:/index";
    }
}
