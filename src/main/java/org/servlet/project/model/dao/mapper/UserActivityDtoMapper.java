package org.servlet.project.model.dao.mapper;

import org.servlet.project.model.dto.UserActivityDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserActivityDtoMapper implements ObjectMapper<UserActivityDto> {

    @Override
    public UserActivityDto extract(ResultSet rs) throws SQLException {
        return UserActivityDto.builder()
                .userId(rs.getLong("user_id"))
                .email(rs.getString("user_email"))
                .activityId(rs.getLong("activity_id"))
                .activityName(rs.getString("activity_name"))
                .categoryId(rs.getLong("category_id"))
                .categoryName(rs.getString("category_name"))
                .build();
    }
}
