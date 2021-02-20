package org.servlet.project;

import org.junit.jupiter.api.Test;
import org.servlet.project.model.dao.impl.ConnectionManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConnectionTest {

    @Test
    public void connectionTest() {

        try (Connection connection = ConnectionManager.getConnection()) {
            assertTrue(connection.isValid(3));
            assertEquals("db_time_management", connection.getCatalog());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
