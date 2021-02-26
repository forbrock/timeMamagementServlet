package org.servlet.project.model.dao.impl;

import org.servlet.project.model.dao.*;

import static org.servlet.project.model.dao.impl.ConnectionManager.getConnection;

public class DaoFactory {

    public static UserDao createUserDao() {
        return new UserDaoImpl(getConnection());
    }

    public static UserActivityDao createUserActivityDao() {
        return new UserActivityDaoImpl(getConnection());
    }

    public static TimeLogDao createTimeLogDao() {
        return new TimeLogDaoImpl(getConnection());
    }

    public static ActivityDao createActivityDao() {
        return new ActivityDaoImpl(getConnection());
    }

    public static CategoryDao createCategoryDao() {
        return new CategoryDaoImpl(getConnection());
    }
}
