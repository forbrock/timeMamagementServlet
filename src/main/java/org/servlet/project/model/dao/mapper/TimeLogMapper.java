package org.servlet.project.model.dao.mapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.servlet.project.model.dto.TimeLogDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

public class TimeLogMapper implements ObjectMapper<TimeLogDto> {
    private static final Logger log = LogManager.getLogger(TimeLogMapper.class);

    @Override
    public TimeLogDto extract(ResultSet rs) throws SQLException {
        TimeLogDto timeLogDto = null;

        try {
            timeLogDto = TimeLogDto.builder()
                    .id(rs.getLong("id"))
                    .userActivityId(rs.getLong("user_activity_id"))
                    .duration(rs.getDouble("duration"))
                    .startDate(rs.getObject("start_date", LocalDateTime.class))
                    .build();
        } catch (SQLException e) {
            log.error("Time log not found", e);
        }
        return timeLogDto;
    }
}
