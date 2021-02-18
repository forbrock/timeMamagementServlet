package org.servlet.project.model.dao;

import org.servlet.project.model.dto.UserActivityDto;

import java.util.List;

public interface UserActivityDao extends Dao<UserActivityDao> {
    List<UserActivityDto> findByUserId(long id);
}
