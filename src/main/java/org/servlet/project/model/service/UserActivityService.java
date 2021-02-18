package org.servlet.project.model.service;

import org.servlet.project.model.dao.impl.JDBCDaoFactory;
import org.servlet.project.model.dao.impl.JDBCUserActivityDao;
import org.servlet.project.model.dto.UserActivityDto;

import java.util.List;

public class UserActivityService {
    private JDBCDaoFactory jdbcDaoFactory;

    public UserActivityService() {
        this.jdbcDaoFactory = new JDBCDaoFactory();
    }

    public List<UserActivityDto> findByUserId(long id) {
        JDBCUserActivityDao uaDao = jdbcDaoFactory.createUserActivityDao();
        return uaDao.findByUserId(id);
    }
}
