package org.servlet.project.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.servlet.project.model.dao.UserActivityDao;
import org.servlet.project.model.dao.mapper.UserActivityDtoMapper;
import org.servlet.project.model.dto.UserActivityDto;
import org.servlet.project.util.DBQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserActivityDaoImpl implements UserActivityDao {
    private static final Logger log = LogManager.getLogger(UserActivityDaoImpl.class);
    private Connection connection;
    private UserActivityDtoMapper uaMapper = new UserActivityDtoMapper();

    public UserActivityDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<UserActivityDto> findByUserId(long id) {
        try (PreparedStatement statement = connection.prepareStatement(DBQueries.FIND_BY_USER_ID_QUERY)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            return extractAllActivities(rs);
        } catch (SQLException e) {
            log.warn("User activities not found: {}", id);
            throw new RuntimeException();
        }
    }

    private List<UserActivityDto> extractAllActivities(ResultSet rs) throws SQLException {
        List<UserActivityDto> list = new ArrayList<>();

        while (rs.next()) {
            UserActivityDto uaDto = uaMapper.extract(rs);
            list.add(uaDto);
        }
        return list;
    }

    @Override
    public Optional<UserActivityDao> findById(long id) {
        return Optional.empty();
    }

    @Override
    public List<UserActivityDao> findAll() {
        return null;
    }

    @Override
    public long save(UserActivityDao userActivityDao) {
        return 0;
    }

    @Override
    public void update(UserActivityDao userActivityDao) {

    }

    @Override
    public void delete(UserActivityDao userActivityDao) {

    }
}
