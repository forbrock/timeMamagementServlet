package org.servlet.project.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
/*
        HttpSession session = request.getSession();
        session.invalidate();
*/
        // TODO: check with redirect
//        return "/WEB-INF/view/login.jsp";
        return "/WEB-INF/view/index.jsp";
    }
}
