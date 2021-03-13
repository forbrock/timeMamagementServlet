package org.servlet.project.model.service;

import org.junit.jupiter.api.Test;
import org.servlet.project.model.dao.UserDao;
import org.servlet.project.model.dao.impl.UserDaoImpl;
import org.servlet.project.model.entity.User;

import java.sql.Connection;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UserServiceTest {
    private final Connection connectionMock = mock(Connection.class);
    private final UserDao userDaoMock = new UserDaoImpl(connectionMock);
    private final SecurityService securityServiceMock = mock(SecurityService.class);
    private final UserService userService = new UserService(userDaoMock, securityServiceMock);

    @Test
    void createTest() {
        userService.create(anyString(), anyString(), anyString(), anyString(), anyString());
        verify(securityServiceMock, times(1)).encrypt(anyString());
        verify(userDaoMock, times(1)).save(any(User.class));
    }
}