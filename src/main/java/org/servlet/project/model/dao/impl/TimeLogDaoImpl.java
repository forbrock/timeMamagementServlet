package org.servlet.project.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.servlet.project.model.dao.TimeLogDao;
import org.servlet.project.model.dao.mapper.TimeLogMapper;
import org.servlet.project.model.dto.TimeLogDto;
import org.servlet.project.util.DBQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TimeLogDaoImpl implements TimeLogDao {
    private static final Logger log = LogManager.getLogger(TimeLogDaoImpl.class);

    private Connection connection;
    private TimeLogMapper timeLogMapper = new TimeLogMapper();

    public TimeLogDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<TimeLogDto> findByUserActivityId(long uaId) {
        try (PreparedStatement statement =
                     connection.prepareStatement(DBQueries.FIND_BY_USER_ACTIVITY_ID)) {
            statement.setLong(1, uaId);
            ResultSet rs = statement.executeQuery();
            return extractAllUserTimeLogs(rs);
        } catch (SQLException e) {
            log.error("Activities with id {} not found", uaId, e);
        }
        return new ArrayList<>();
    }

    private List<TimeLogDto> extractAllUserTimeLogs(ResultSet rs) throws SQLException {
        List<TimeLogDto> timeLogs = new ArrayList<>();

        while (rs.next()) {
            TimeLogDto dto = timeLogMapper.extract(rs);
            timeLogs.add(dto);
        }
        return timeLogs;
    }

    @Override
    public boolean saveTime(String uaId, String time) {
        try (PreparedStatement statement =
                     connection.prepareStatement(DBQueries.SAVE_NEW_TIME_POINT_QUERY)) {

            statement.setDouble(1, Double.parseDouble(time));
            statement.setLong(2, Long.parseLong(uaId));
            statement.setObject(3, LocalDateTime.now());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            log.error("Can't save the time point", e);
        }
        return false;
    }

    @Override
    public Optional<TimeLogDto> findById(long id) {
        return Optional.empty();
    }

    @Override
    public List<TimeLogDto> findAll() {
        return null;
    }

    @Override
    public long save(TimeLogDto timeLogDto) {
        return 0;
    }

    @Override
    public void update(TimeLogDto timeLogDto) {

    }

    @Override
    public void delete(TimeLogDto timeLogDto) {

    }
}
