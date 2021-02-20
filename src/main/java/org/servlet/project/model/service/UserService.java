package org.servlet.project.model.service;

import org.servlet.project.model.dao.UserDao;
import org.servlet.project.model.dao.impl.DaoFactory;
import org.servlet.project.model.entity.User;

import java.util.Optional;

public class UserService {
    private final UserDao userDao = DaoFactory.createUserDao();

    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
