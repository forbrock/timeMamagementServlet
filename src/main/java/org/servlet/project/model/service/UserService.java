package org.servlet.project.model.service;

import org.servlet.project.exceptions.UserAlreadyExistException;
import org.servlet.project.model.dao.UserDao;
import org.servlet.project.model.dao.impl.DaoFactory;
import org.servlet.project.model.entity.Role;
import org.servlet.project.model.entity.User;

import java.util.Optional;

public class UserService {
    private final UserDao userDao = DaoFactory.createUserDao();
    private final SecurityService securityService = new SecurityService();

    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public User createUser(String firstName,
                           String lastName,
                           String email,
                           String password) throws UserAlreadyExistException {
        String encodedPassword = securityService.encrypt(password);
        return userDao.save(User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(encodedPassword)
                .build());
    }
}
