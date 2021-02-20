package org.servlet.project.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class TimeCommand implements Command {
    private static final Logger log = LogManager.getLogger(TimeCommand.class);
    @Override
    public String execute(HttpServletRequest request) {
        log.info("time");
        request.getParameter("userActionId");
        request.getParameter("time");

        return "redirect:/index";
    }
}
