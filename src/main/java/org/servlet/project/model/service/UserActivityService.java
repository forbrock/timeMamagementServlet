package org.servlet.project.model.service;

import org.servlet.project.model.dao.UserActivityDao;
import org.servlet.project.model.dao.impl.DaoFactory;
import org.servlet.project.model.dto.UserActivityDto;
import org.servlet.project.model.entity.ActivityState;
import org.servlet.project.model.entity.User;
import org.servlet.project.model.entity.UserActivity;

import java.util.List;

public class UserActivityService {

    private final UserActivityDao uaDao = DaoFactory.createUserActivityDao();

    public List<UserActivityDto> findByUserId(long id) {
        return uaDao.findByUserId(id);
    }

    public boolean completeTask(long id) {
        return uaDao.updateActivityState(ActivityState.COMPLETED, id);
    }

    public UserActivity createRequest(long userId, long activityId) {
        return uaDao.save(UserActivity.builder()
                .userId(userId)
                .activityId(activityId)
                .state(ActivityState.REQUESTED)
                .build());
    }

    public List<UserActivityDto> findAll() {
        return uaDao.findAll();
    }

    public boolean acceptActivity(long id) {
        return uaDao.updateActivityState(ActivityState.ACCEPTED, id);
    }

    public boolean delete(long id) {
        return uaDao.delete(id);
    }
}
