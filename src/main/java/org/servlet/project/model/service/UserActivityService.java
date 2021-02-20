package org.servlet.project.model.service;

import org.servlet.project.model.dao.UserActivityDao;
import org.servlet.project.model.dao.impl.DaoFactory;
import org.servlet.project.model.dto.UserActivityDto;

import java.util.List;

public class UserActivityService {

    private final UserActivityDao uaDao = DaoFactory.createUserActivityDao();

    public List<UserActivityDto> findByUserId(long id) {
        return uaDao.findByUserId(id);
    }
}
