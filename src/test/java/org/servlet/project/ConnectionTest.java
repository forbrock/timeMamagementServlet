package org.servlet.project;

import org.junit.jupiter.api.Test;
import org.servlet.project.model.dao.impl.JDBCConnectionManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConnectionTest {

    @Test
    public void connectionTest() {
        DataSource dataSource = JDBCConnectionManager.getDataSource();

        try (Connection connection = dataSource.getConnection()) {
            assertTrue(connection.isValid(3));
            assertEquals("db_time_management", connection.getCatalog());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
