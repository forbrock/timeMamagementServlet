package org.servlet.project.dao;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.servlet.project.model.dao.UserDao;
import org.servlet.project.model.dao.impl.ConnectionManager;
import org.servlet.project.model.dao.impl.DaoFactory;
import org.servlet.project.model.dao.impl.UserDaoImpl;
import org.servlet.project.model.entity.Role;
import org.servlet.project.model.entity.User;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class UserDaoImplTest {
    private static DaoFactory daoFactory;
    private static UserDao userDao;

    @BeforeAll
    public static void init() {
        daoFactory = new DaoFactory();
        userDao = daoFactory.createUserDao();
    }

    @Test
    public void connection_test() {
        Connection connection = ConnectionManager.getConnection();
        try {
            assertTrue(connection.isValid(3));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findById_test() {
        User expectedUser = User.builder()
                .id(2)
                .firstName("user1")
                .lastName("user1")
                .email("user1@mail.com")
                .password("111")
                .enabled(true)
                .role(Role.USER)
                .build();

        User user = userDao.findById(2).orElseThrow();
        assertEquals(expectedUser, user);
    }
}
