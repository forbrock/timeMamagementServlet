package org.servlet.project.model.service;

import org.servlet.project.model.dao.TimeLogDao;
import org.servlet.project.model.dao.UserActivityDao;
import org.servlet.project.model.dao.impl.DaoFactory;
import org.servlet.project.model.dto.CombineUaDto;
import org.servlet.project.model.dto.TimeLogDto;
import org.servlet.project.model.dto.UserActivityDto;

import java.util.List;

public class UserActivityService {

    private final UserActivityDao uaDao = DaoFactory.createUserActivityDao();
    private final TimeLogDao timeLogDto = DaoFactory.createTimeLogDao();
    private final SecurityService securityService = new SecurityService();

    public List<UserActivityDto> findByUserId(long id) {
        return uaDao.findByUserId(id);
    }

    public List<CombineUaDto> combineUserActivities(List<UserActivityDto> userActivities) {
//        List<TimeLogDto> timeLogs = timeLogDto.findByUserActivityId()
        return null;
    }
}
