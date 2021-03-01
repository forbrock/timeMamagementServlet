package org.servlet.project.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.servlet.project.exceptions.ActivityAlreadyExistException;
import org.servlet.project.model.dao.ActivityDao;
import org.servlet.project.model.dao.mapper.ActivityMapper;
import org.servlet.project.model.entity.Activity;
import org.servlet.project.util.DBQueries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ActivityDaoImpl implements ActivityDao {
    private static final Logger log = LogManager.getLogger(ActivityDaoImpl.class);

    private Connection connection;
    private final ActivityMapper activityMapper = new ActivityMapper();

    public ActivityDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Activity> findAll() {
        try (PreparedStatement statement =
                     connection.prepareStatement(DBQueries.FIND_ALL_ACTIVITIES_QUERY)) {
            ResultSet rs = statement.executeQuery();

            return activityMapper.extractAll(rs);
        } catch (SQLException e) {
            log.error("Activities not found", e);
        }
        return new ArrayList<>();
    }

    @Override
    public Optional<Activity> update(Activity activity) {
        try (PreparedStatement statement =
                     connection.prepareStatement(DBQueries.UPDATE_ACTIVITY_QUERY)) {
            statement.setString(1, activity.getName());
            statement.setLong(2, activity.getId());
            boolean isUpdated = statement.executeUpdate() > 0;
            if (isUpdated) {
                return Optional.of(activity);
            }
        } catch (SQLIntegrityConstraintViolationException ex1) {
            throw new ActivityAlreadyExistException();
        } catch (SQLException ex2) {
            log.error("Can not provide activity update operation", ex2);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Activity> findById(long id) {
        try (PreparedStatement statement =
                connection.prepareStatement(DBQueries.FIND_ACTIVITY_BY_ID_QUERY)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return Optional.ofNullable(activityMapper.extract(rs));
            }
        } catch (SQLException e) {
            log.error("Can not provide activity findById operation", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Activity> save(Activity activity) {
        try (PreparedStatement statement =
                     connection.prepareStatement(DBQueries.SAVE_ACTIVITY_QUERY)) {
            statement.setString(1, activity.getName());
            statement.setLong(2, activity.getCategoryId());
            boolean saved = statement.executeUpdate() > 0;
            if (saved) {
                return Optional.of(activity);
            }
        } catch (SQLIntegrityConstraintViolationException ex1) {
            log.info("Attempt to create an existing activity: {}", activity.getName());
            throw new ActivityAlreadyExistException();
        } catch (SQLException ex2) {
            log.error("Can not provide activity save operation", ex2);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Activity> delete(Activity activity) {
        return Optional.empty();
    }
}
