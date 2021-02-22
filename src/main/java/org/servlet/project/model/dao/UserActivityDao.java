package org.servlet.project.model.dao;

import org.servlet.project.exceptions.ActivityAlreadyExistException;
import org.servlet.project.model.dto.UserActivityDto;
import org.servlet.project.model.entity.ActivityState;
import org.servlet.project.model.entity.UserActivity;

import java.util.List;

public interface UserActivityDao extends Dao<UserActivityDto> {
    List<UserActivityDto> findByUserId(long id);
    boolean updateActivityState(ActivityState state, long id);
    UserActivity save(UserActivity ua) throws ActivityAlreadyExistException;
}
