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
            "       RIGHT JOIN users_activities ua ON time_log.user_activity_id = ua.id" +
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

    public static final String FIND_REQUESTED_AND_CURRENT_ACTIVITIES = "SELECT" +
            " *" +
            " FROM users_activities" +
            " WHERE user_id = ?" +
            "   AND activity_id = ?" +
            "   AND state IN ('ACCEPTED', 'REQUESTED')";

    public static final String FIND_BY_ID_QUERY = "SELECT * FROM users WHERE users.id = ?";
    public static final String FIND_BY_USER_EMAIL_QUERY = "SELECT * FROM users WHERE users.email = ?";
    public static final String SAVE_NEW_TIME_POINT_QUERY = "INSERT INTO time_log (duration, user_activity_id, start_date) VALUES (?, ?, ?)";
    public static final String UPDATE_ACTIVITY_STATUS_QUERY = "UPDATE users_activities SET state = ? WHERE id = ?";
    public static final String FIND_ALL_ACTIVITIES_QUERY = "SELECT * FROM activities";
    public static final String SAVE_USER_ACTIVITY_QUERY = "INSERT INTO users_activities (user_id, activity_id, state) VALUES (?, ?, ?)";
    public static final String DELETE_USER_ACTIVITY_BY_ID_QUERY = "DELETE FROM users_activities WHERE id = ?";
    public static final String SAVE_USER_QUERY = "INSERT INTO users (first_name, last_name, email, password, role) VALUES (?, ?, ?, ?, ?)";
    public static final String DELETE_USER_BY_ID = "DELETE FROM users WHERE id = ?";
    public static final String FIND_ALL_USERS_QUERY = "SELECT * FROM users";
    public static final String UPDATE_USER_QUERY = "UPDATE users SET first_name = ?, last_name = ?, password = ?, enabled = ?, role = ? WHERE id = ?";
    public static final String UPDATE_USER_SAME_PASSWORD_QUERY = "UPDATE users SET first_name = ?, last_name = ?, enabled = ?, role = ? WHERE id = ?";
    public static final String FIND_ALL_CATEGORIES_QUERY = "SELECT * FROM categories";
    public static final String UPDATE_CATEGORY_QUERY = "UPDATE categories SET name = ? WHERE id = ?";
    public static final String FIND_CATEGORY_BY_ID_QUERY = "SELECT * FROM categories WHERE id = ?";
    public static final String SAVE_CATEGORY_QUERY = "INSERT INTO categories (name) VALUES (?)";
    public static final String DELETE_CATEGORY_BY_ID_QUERY = "DELETE FROM categories WHERE id = ?";
    public static final String UPDATE_ACTIVITY_QUERY = "UPDATE activities SET name = ? WHERE id = ?";
    public static final String FIND_ACTIVITY_BY_ID_QUERY = "SELECT * FROM activities WHERE id = ?";
    public static final String SAVE_ACTIVITY_QUERY = "INSERT INTO activities (name, category_id) VALUES (?, ?)";
}
