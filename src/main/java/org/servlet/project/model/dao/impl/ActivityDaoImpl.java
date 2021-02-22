package org.servlet.project.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.servlet.project.model.dao.ActivityDao;
import org.servlet.project.model.dao.mapper.ActivityMapper;
import org.servlet.project.model.entity.Activity;
import org.servlet.project.util.DBQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public Optional<Activity> findById(long id) {
        return Optional.empty();
    }

    @Override
    public Activity save(Activity activity) {
        return null;
    }

    @Override
    public Activity update(Activity activity) {
        return null;
    }

    @Override
    public Activity delete(Activity activity) {
        return null;
    }
}
