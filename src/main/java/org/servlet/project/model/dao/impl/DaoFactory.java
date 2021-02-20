package org.servlet.project.model.dao.impl;

import org.servlet.project.model.dao.UserActivityDao;
import org.servlet.project.model.dao.UserDao;

import static org.servlet.project.model.dao.impl.ConnectionManager.getConnection;

public class DaoFactory {

    public static UserDao createUserDao() {
        return new UserDaoImpl(getConnection());
    }

    public static UserActivityDao createUserActivityDao() {
        return new UserActivityDaoImpl(getConnection());
    }
}
