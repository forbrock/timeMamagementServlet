package org.servlet.project.model.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionalFactory extends DaoFactory {
    private final Connection connection = ConnectionManager.getConnection();

    public void begin() throws SQLException {
        connection.setAutoCommit(false);
    }

    public void commit() throws SQLException {
        connection.commit();
        connection.setAutoCommit(true);
    }

    public void rollback() throws SQLException {
        connection.rollback();
        connection.setAutoCommit(true);
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
