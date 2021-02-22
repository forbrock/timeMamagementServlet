package org.servlet.project.controller.command;

import javax.servlet.http.HttpServletRequest;

import static org.servlet.project.util.ViewResolver.resolve;

public class Registration implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        return resolve("registration");
    }
}
