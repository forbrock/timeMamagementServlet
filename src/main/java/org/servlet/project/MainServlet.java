package org.servlet.project;

import org.servlet.project.controller.command.*;
import org.servlet.project.model.service.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.servlet.project.util.ViewResolver.resolve;

public class MainServlet extends HttpServlet {
    private final Map<String, Command> commands = new HashMap<>();

    private final UserService userService = new UserService();
    private final UserActivityService userActivityService = new UserActivityService();
    private final SecurityService securityService = new SecurityService();
    private final TimeLogService timeLogService = new TimeLogService();
    private final ActivityService activityService = new ActivityService();
    private final CategoryService categoryService = new CategoryService();

    public void init(ServletConfig servletConfig) {
        Index indexCommand = new Index(userService, userActivityService, securityService, activityService);

        commands.put("logout", new Logout());
        commands.put("login", new Login(userService, securityService));
        commands.put("registration", new Registration(userService));
        commands.put("index", indexCommand);
        commands.put("/", indexCommand);
        commands.put("time", new Time(timeLogService));
        commands.put("complete", new CompleteActivity(userActivityService));
        commands.put("request/activity", new RequestActivity(userActivityService, securityService));
        commands.put("admin", new Admin(userActivityService));
        commands.put("report", new AdminReport(userActivityService));
        commands.put("admin/request/accept", new AdminAcceptActivity(userActivityService));
        commands.put("admin/request/decline", new RejectRequest(userActivityService));
        commands.put("admin/users", new AdminUsers(userService));
        commands.put("admin/user/create", new AdminCreateUser(userService));
        commands.put("admin/user/edit", new AdminUserEdit(userService));
        commands.put("admin/user/edit.do", new AdminUserEditDo(userService));
        commands.put("admin/user/delete", new AdminUserDelete(userService));
        commands.put("admin/user/activities", new AdminUserActivities(userActivityService));
        commands.put("admin/categories", new AdminCategories(categoryService));
    }

    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response) throws ServletException, IOException {
        String contextPath = request.getContextPath();
        String path = request.getRequestURI();
        path = path.replace(contextPath, "").replaceFirst("/", "");
        if (path.isEmpty()) {
            path = "index";
        }
        Command command = commands.getOrDefault(path, (def) -> resolve("login"));
        String page = command.execute(request);

        if (page.contains("redirect:")) {
            String redirectStr = contextPath + page.replace("redirect:", "");
            response.sendRedirect(redirectStr);
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
