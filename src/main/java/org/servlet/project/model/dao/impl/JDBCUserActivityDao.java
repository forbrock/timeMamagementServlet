package org.servlet.project.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.servlet.project.model.dao.UserActivityDao;
import org.servlet.project.model.dao.mapper.UserActivityDtoMapper;
import org.servlet.project.model.dto.UserActivityDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCUserActivityDao implements UserActivityDao {
    private static final Logger log = LogManager.getLogger(JDBCUserActivityDao.class);

    private static final String FIND_BY_USER_ID_QUERY = "SELECT " +
            " users_activities.id AS id," +
            " u.id AS user_id," +
            " u.email AS user_email," +
            " a.id AS activity_id," +
            " a.name AS activity_name," +
            " c.id AS category_id," +
            " c.name AS category_name" +
            " FROM users_activities" +
            " JOIN users u ON users_activities.user_id = u.id" +
            " JOIN activities a ON users_activities.activity_id = a.id" +
            " JOIN categories c ON a.category_id = c.id" +
            " WHERE user_id = ?";

    private Connection connection;
    private UserActivityDtoMapper uaMapper = new UserActivityDtoMapper();

    public JDBCUserActivityDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<UserActivityDto> findByUserId(long id) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_USER_ID_QUERY)) {
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
