package org.servlet.project.controller.command;

import javax.servlet.http.HttpServletRequest;

import static org.servlet.project.util.ViewResolver.resolve;

public class RegistrationCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return resolve("registration");
    }
}
