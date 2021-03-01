package org.servlet.project.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.servlet.project.model.dao.TimeLogDao;
import org.servlet.project.model.dto.TimeLogDto;
import org.servlet.project.util.DBQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class TimeLogDaoImpl implements TimeLogDao {
    private static final Logger log = LogManager.getLogger(TimeLogDaoImpl.class);

    private Connection connection;

    public TimeLogDaoImpl(Connection connection) {
        this.connection = connection;
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
    public Optional<TimeLogDto> save(TimeLogDto timeLogDto) {
        return Optional.empty();
    }

    @Override
    public Optional<TimeLogDto> update(TimeLogDto timeLogDto) {
        return Optional.empty();
    }

    @Override
    public Optional<TimeLogDto> delete(TimeLogDto timeLogDto) {
        return Optional.empty();
    }
}
