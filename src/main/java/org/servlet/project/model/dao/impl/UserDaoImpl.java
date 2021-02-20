package org.servlet.project.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.servlet.project.model.dao.UserDao;
import org.servlet.project.model.dao.mapper.UserMapper;
import org.servlet.project.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final Logger log = LogManager.getLogger(UserDaoImpl.class);

    private static final String FIND_BY_ID_QUERY = "select * from users where users.id = ?";
    private static final String FIND_BY_USERNAME_QUERY = "select * from users where users.email = ?";

    private Connection connection;
    private UserMapper userMapper = new UserMapper();

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<User> findById(long id) {
        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_ID_QUERY)){
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            return Optional.ofNullable(userMapper.extract(rs));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_USERNAME_QUERY)) {
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();

            return Optional.ofNullable(userMapper.extract(rs));
        } catch (SQLException e) {
            log.warn("User not found: {}", email);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public long save(User user) {
        return 0;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }
}
