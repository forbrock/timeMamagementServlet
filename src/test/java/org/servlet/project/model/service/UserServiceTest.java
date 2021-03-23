package org.servlet.project.model.service;

import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.servlet.project.model.dao.UserDao;
import org.servlet.project.model.dao.impl.UserDaoImpl;
import org.servlet.project.model.entity.Role;
import org.servlet.project.model.entity.User;

import java.sql.Connection;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UserServiceTest {
    private final UserDao userDaoMock = mock(UserDaoImpl.class);
    private final SecurityService securityServiceMock = mock(SecurityService.class);
    private final UserService userService = new UserService(userDaoMock, securityServiceMock);

    @Test
    void create_test() {
        User user = User.builder()
                .firstName("firstName")
                .lastName("lastName")
                .email("user@mail.com")
                .password("1234")
                .role(Role.USER)
                .build();
        userService.create("firstName", "lastName",
                "user@mail.com", "123", Role.USER.name());
        verify(securityServiceMock).encrypt("123");
        verify(userDaoMock).save(user);
    }

    @Test
    void findByEmail_test() {
        String email = "user@mail.com";
        userService.findByEmail(email);
        verify(userDaoMock).findByEmail(email);
    }

    @Test
    void whenNoPassAndDisabled_update_test() {
        User user = User.builder()
                .id(3)
                .firstName("firstName")
                .lastName("lastName")
                .email("user@mail.com")
                .role(Role.USER)
                .build();
        when(userDaoMock.update(any(User.class))).thenReturn(Optional.of(user));
        Optional<User> updatedUser = userService.update("3", "firstName",
                "lastName", "", Role.USER.name(), "");
        assertTrue(updatedUser.isPresent());
        assertEquals(user, updatedUser.get());
        verify(securityServiceMock, never()).encrypt(anyString());
        verify(userDaoMock).update(any(User.class));
    }

    @Test
    void whenPassChanged_update_test() {
        User user = User.builder()
                .id(3)
                .firstName("firstName")
                .lastName("lastName")
                .email("user@mail.com")
                .role(Role.USER)
                .build();
        when(userDaoMock.update(any(User.class))).thenReturn(Optional.of(user));
        userService.update("3", "firstName",
                "lastName", "123", Role.USER.name(), "");
        verify(securityServiceMock).encrypt("123");
        verify(userDaoMock).update(any(User.class));
    }

    @Test
    void findAll_test() {
        userService.findAll();
        verify(userDaoMock).findAll();
    }

    @Test
    void findById_test() {
        long id = 12;
        userService.findById(id);
        verify(userDaoMock).findById(id);
    }

    @Test
    void delete_test() {
        long id = 12;
        userService.delete(id);
        verify(userDaoMock).deleteById(id);
    }
}