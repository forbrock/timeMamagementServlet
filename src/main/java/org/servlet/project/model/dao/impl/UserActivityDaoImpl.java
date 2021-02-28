package org.servlet.project.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.servlet.project.exceptions.ActivityAlreadyExistException;
import org.servlet.project.model.dao.UserActivityDao;
import org.servlet.project.model.dao.mapper.UserActivityDtoMapper;
import org.servlet.project.model.dto.UserActivityDto;
import org.servlet.project.model.entity.ActivityState;
import org.servlet.project.model.entity.UserActivity;
import org.servlet.project.util.DBQueries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserActivityDaoImpl implements UserActivityDao {
    private static final Logger log = LogManager.getLogger(UserActivityDaoImpl.class);
    private Connection connection;
    private final UserActivityDtoMapper uaMapper = new UserActivityDtoMapper();

    public UserActivityDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<UserActivityDto> findByUserId(long id) {
        try (PreparedStatement statement = connection.prepareStatement(DBQueries.FIND_BY_USER_ID_QUERY)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            return uaMapper.extractAll(rs);
        } catch (SQLException e) {
            log.warn("User activities not found: {}", id);
            throw new RuntimeException();
        }
    }

    @Override
    public boolean updateActivityState(ActivityState state, long id) {
        try (PreparedStatement statement =
                     connection.prepareStatement(DBQueries.UPDATE_ACTIVITY_STATUS_QUERY)) {
            statement.setString(1, state.name());
            statement.setLong(2, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            log.error("Failed to update status", e);
        }
        return false;
    }

    @Override
    public UserActivity save(UserActivity ua) {
        if (checkRequested(ua)) {
            log.info("Such activity already requested, id: {}", ua.getActivityId());
            throw new ActivityAlreadyExistException();
        }

        try (PreparedStatement statement =
                     connection.prepareStatement(DBQueries.SAVE_USER_ACTIVITY_QUERY)) {

            statement.setLong(1, ua.getUserId());
            statement.setLong(2, ua.getActivityId());
            statement.setString(3, ua.getState().name());
            statement.executeUpdate();
        } catch (SQLException ex1) {
            log.error("Can't save new user activity request", ex1);
        }
        return ua;
    }

    @Override
    public boolean delete(long id) {
        try (PreparedStatement statement =
                connection.prepareStatement(DBQueries.DELETE_USER_ACTIVITY_BY_ID_QUERY)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            log.error(e);
        }
        return false;
    }

    private boolean checkRequested(UserActivity ua) {
        try (PreparedStatement statement =
                connection.prepareStatement(DBQueries.FIND_REQUESTED_AND_CURRENT_ACTIVITIES)) {
            statement.setLong(1, ua.getUserId());
            statement.setLong(2, ua.getActivityId());
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            log.error(e);
        }
        return false;
    }

    @Override
    public List<UserActivityDto> findAll() {
        try (PreparedStatement statement =
                connection.prepareStatement(DBQueries.FIND_ALL_USERS_ACTIVITIES_QUERY)) {
            ResultSet rs = statement.executeQuery();
            return uaMapper.extractAll(rs);
        } catch (SQLException e) {
            log.error("ERROR: Can't find any activities", e);
        }
        return new ArrayList<>();
    }

    @Override
    public UserActivityDto save(UserActivityDto uaDto) {
        return null;
    }

    @Override
    public Optional<UserActivityDto> findById(long id) {
        return Optional.empty();
    }

    @Override
    public UserActivityDto update(UserActivityDto uaDto) {
        return null;
    }

    @Override
    public UserActivityDto delete(UserActivityDto uaDto) {
        return null;
    }
}
