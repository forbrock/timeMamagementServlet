package org.servlet.project.model.dao.impl;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.util.ResourceBundle;

public class JDBCConnectionManager {
    private static BasicDataSource ds;
    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;
    private static final int DEFAULT_MAX_IDLE = 10;
    private static final int DEFAULT_MIN_IDLE = 5;
    private static final int DEFAULT_MAX_OPEN_PREPARED_STATEMENTS = 20;

    static {
//        registerDriver();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
        URL = resourceBundle.getString("database.url");
        USER = resourceBundle.getString("database.user");
        PASSWORD = resourceBundle.getString("database.password");
    }

    public static DataSource getDataSource() {
        if (ds == null) {
            synchronized (JDBCConnectionManager.class) {
                if (ds == null) {
                    ds = new BasicDataSource();
                    ds.setMaxIdle(DEFAULT_MAX_IDLE);
                    ds.setMinIdle(DEFAULT_MIN_IDLE);
                    ds.setMaxOpenPreparedStatements(DEFAULT_MAX_OPEN_PREPARED_STATEMENTS);
                    ds.setUrl(URL);
                    ds.setUsername(USER);
                    ds.setPassword(PASSWORD);
                }
            }
        }
        return ds;
    }

    private static void registerDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
