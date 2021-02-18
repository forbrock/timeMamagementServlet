package org.servlet.project.model.service;

import org.servlet.project.model.dao.impl.JDBCDaoFactory;
import org.servlet.project.model.dao.impl.JDBCUserDao;
import org.servlet.project.model.entity.User;

import java.util.Optional;

public class UserService {
    private JDBCDaoFactory jdbcDaoFactory;

    public UserService() {
        this.jdbcDaoFactory = new JDBCDaoFactory();
    }

    public Optional<User> findByEmail(String email) {
        JDBCUserDao userDao = jdbcDaoFactory.createUserDao();
        return userDao.findByEmail(email);
    }
}
