package org.servlet.project.model.dao.impl;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionManager {
    private static final Logger log = LogManager.getLogger(ConnectionManager.class);

    private static BasicDataSource ds;
    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;
    private static final int DEFAULT_MAX_IDLE = 10;
    private static final int DEFAULT_MIN_IDLE = 5;
    private static final int DEFAULT_MAX_OPEN_PREPARED_STATEMENTS = 20;

    static {
        registerDriver();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
        URL = resourceBundle.getString("database.url");
        USER = resourceBundle.getString("database.user");
        PASSWORD = resourceBundle.getString("database.password");
    }

    private static BasicDataSource getDataSource() {
        if (ds == null) {
            synchronized (ConnectionManager.class) {
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

    public static Connection getConnection(){
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    private static void registerDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
