package org.servlet.project.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class Logout implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (Objects.nonNull(session)) {
            session.invalidate();
        }
        return "redirect:/login";
    }
}
