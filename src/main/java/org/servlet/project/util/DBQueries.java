package org.servlet.project.util;

public class DBQueries {
    public static final String FIND_BY_USER_ID_QUERY = "SELECT" +
            " u.email       AS email," +
            " c.name        AS category," +
            " a.name        AS activity," +
            " ua.id         AS id," +
            " ua.state      AS state," +
            " SUM(duration) AS duration" +
            " FROM time_log" +
            "       LEFT JOIN users_activities ua ON time_log.user_activity_id = ua.id" +
            "       LEFT JOIN users u             ON ua.user_id = u.id" +
            "       LEFT JOIN activities a        ON ua.activity_id = a.id" +
            "       LEFT JOIN categories c        ON a.category_id = c.id" +
            " WHERE u.id = ?" +
            " GROUP BY ua.id";

    public static final String FIND_ALL_USERS_ACTIVITIES_QUERY = "SELECT" +
            " u.email       AS email," +
            " c.name        AS category," +
            " a.name        AS activity," +
            " ua.id         AS id," +
            " ua.state      AS state," +
            " SUM(duration) AS duration" +
            " FROM time_log" +
            "         RIGHT JOIN users_activities ua ON time_log.user_activity_id = ua.id" +
            "         LEFT JOIN users u              ON ua.user_id = u.id" +
            "         LEFT JOIN activities a         ON ua.activity_id = a.id" +
            "         LEFT JOIN categories c         ON a.category_id = c.id" +
            " GROUP BY ua.id";

    public static final String FIND_BY_ID_QUERY = "SELECT * FROM users WHERE users.id = ?";
    public static final String FIND_BY_USER_EMAIL_QUERY = "SELECT * FROM users WHERE users.email = ?";
    public static final String SAVE_NEW_TIME_POINT_QUERY = "INSERT INTO time_log (duration, user_activity_id, start_date) VALUES (?, ?, ?)";
    public static final String UPDATE_ACTIVITY_STATUS_QUERY = "UPDATE users_activities SET state = ? WHERE id = ?";
    public static final String FIND_ALL_ACTIVITIES_QUERY = "SELECT * FROM activities";
    public static final String SAVE_USER_ACTIVITY_QUERY = "INSERT INTO users_activities (user_id, activity_id, state) VALUES (?, ?, ?)";
}
