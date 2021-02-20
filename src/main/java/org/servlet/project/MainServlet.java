package org.servlet.project;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.servlet.project.controller.command.*;
import org.servlet.project.model.service.SecurityService;
import org.servlet.project.model.service.UserActivityService;
import org.servlet.project.model.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class MainServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(MainServlet.class);
    private Map<String, Command> commands = new HashMap<>();

    private final UserService userService = new UserService();
    private final UserActivityService userActivityService = new UserActivityService();
    private final SecurityService securityService = new SecurityService();

    public void init(ServletConfig servletConfig) {
        servletConfig.getServletContext().setAttribute("loggedUsers", new HashSet<String>());

        commands.put("/logout", new LogoutCommand());
        commands.put("/login", new LoginCommand(userService, securityService));
        commands.put("/registration", new RegistrationCommand());
        commands.put("/index", new IndexCommand(userService, userActivityService, securityService));
    }

    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response) throws ServletException, IOException {

        String path = request.getRequestURI()
                .replaceFirst(request.getContextPath() + "/app", "");
        Command command = commands.getOrDefault(path, (def) -> "/WEB-INF/view/index.jsp");
        String page = command.execute(request);

        if (page.contains("redirect:")) {
            response.sendRedirect(request.getContextPath() +
                    request.getServletPath() + page.replace("redirect:", ""));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }
}