package org.servlet.project.model.dao.mapper;

import org.servlet.project.model.dto.UserActivityDto;
import org.servlet.project.model.entity.ActivityState;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserActivityDtoMapper implements ObjectMapper<UserActivityDto> {

    @Override
    public UserActivityDto extract(ResultSet rs) throws SQLException {
        return UserActivityDto.builder()
                .id(rs.getLong("id"))
                .email(rs.getString("email"))
                .activity(rs.getString("activity"))
                .category(rs.getString("category"))
                .state(ActivityState.valueOf(rs.getString("state")))
                .duration(rs.getDouble("duration"))
                .build();
    }
}
