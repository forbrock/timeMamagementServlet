package org.servlet.project.util;

public class DBQueries {
    public static final String FIND_BY_USER_ID_QUERY = "SELECT" +
            " users_activities.id AS id," +
            " u.id AS user_id," +
            " u.email AS user_email," +
            " a.id AS activity_id," +
            " a.name AS activity_name," +
            " c.id AS category_id," +
            " c.name AS category_name," +
            " users_activities.state AS state" +
            " FROM users_activities" +
            " LEFT JOIN users u ON users_activities.user_id = u.id" +
            " LEFT JOIN activities a ON users_activities.activity_id = a.id" +
            " LEFT JOIN categories c ON a.category_id = c.id" +
            " WHERE user_id = ?";

    public static final String FIND_BY_ID_QUERY = "SELECT * FROM users WHERE users.id = ?";
    public static final String FIND_BY_USERNAME_QUERY = "SELECT * FROM users WHERE users.email = ?";
    public static final String SAVE_NEW_TIME_POINT_QUERY = "INSERT INTO time_log (duration, user_activity_id, start_date) VALUES (?, ?, ?)";
    public static final String FIND_BY_USER_ACTIVITY_ID = "SELECT * FROM time_log WHERE user_activity_id = ?";
}
