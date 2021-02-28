package org.servlet.project.util;

import org.servlet.project.controller.command.*;
import org.servlet.project.model.service.*;

import java.util.HashMap;
import java.util.Map;

public class CommandResolver {
    private static final UserService userService = new UserService();
    private static final UserActivityService userActivityService = new UserActivityService();
    private static final SecurityService securityService = new SecurityService();
    private static final TimeLogService timeLogService = new TimeLogService();
    private static final ActivityService activityService = new ActivityService();
    private static final CategoryService categoryService = new CategoryService();

    public static final Index indexCommand = new Index(userService, userActivityService, securityService, activityService);

    public static final Map<String, Command> commands = new HashMap<>() {{
        put("logout", new Logout());
        put("login", new Login(userService, securityService));
        put("registration", new Registration(userService));
        put("index", indexCommand);
        put("/", indexCommand);
        put("time", new Time(timeLogService));
        put("complete", new CompleteActivity(userActivityService));
        put("request/activity", new RequestActivity(userActivityService, securityService));
        put("admin", new Admin(userActivityService));
        put("admin/report", new AdminReport(userActivityService));
        put("admin/request/accept", new AdminAcceptActivity(userActivityService));
        put("admin/request/decline", new RejectRequest(userActivityService));
        put("admin/users", new AdminUsers(userService));
        put("admin/user/create", new AdminCreateUser(userService));
        put("admin/user/edit", new AdminUserEdit(userService));
        put("admin/user/edit.do", new AdminUserEditDo(userService));
        put("admin/user/delete", new AdminUserDelete(userService));
        put("admin/user/activities", new AdminUserActivities(userActivityService));
        put("admin/categories", new AdminCategories(categoryService));
        put("admin/category/edit", new AdminCategoryEdit(categoryService));
        put("admin/category/create", new AdminCreateCategory(categoryService));
        put("admin/category/delete", new AdminCategoryDelete(categoryService));
        put("admin/activities", new AdminActivities(activityService, categoryService));
        put("admin/activity/edit", new AdminActivityEdit(activityService, categoryService));
        put("admin/activity/create", new AdminCreateActivity(activityService));
    }};
}
