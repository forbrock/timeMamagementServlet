package org.servlet.project.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.servlet.project.exceptions.UserAlreadyExistException;
import org.servlet.project.model.dao.UserDao;
import org.servlet.project.model.dao.mapper.UserMapper;
import org.servlet.project.model.entity.User;
import org.servlet.project.util.DBQueries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final Logger log = LogManager.getLogger(UserDaoImpl.class);

    private Connection connection;
    private final UserMapper userMapper = new UserMapper();

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<User> findById(long id) {
        try (PreparedStatement ps = connection.prepareStatement(DBQueries.FIND_BY_ID_QUERY)){
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.ofNullable(userMapper.extract(rs));
            }
        } catch (SQLException e) {
            log.warn("ERROR: can't provide user findById operation!", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (PreparedStatement statement = connection.prepareStatement(DBQueries.FIND_BY_USER_EMAIL_QUERY)) {
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return Optional.ofNullable(userMapper.extract(rs));
            }
        } catch (SQLException e) {
            log.warn("ERROR: can't provide user findByEmail operation!", e);
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteById(long id) {
        try (PreparedStatement statement =
                connection.prepareStatement(DBQueries.DELETE_USER_BY_ID)) {
            statement.setLong(1, id);
            boolean isDeleted = statement.executeUpdate() > 0;
            if (isDeleted) {
                log.info("Deleted user id: {}", id);
                return true;
            }
        } catch (SQLException e) {
            log.error("ERROR: can't provide user delete operation!", e);
        }
        return false;
    }

    @Override
    public Optional<User> save(User user) {
        try (PreparedStatement statement =
                connection.prepareStatement(DBQueries.SAVE_USER_QUERY)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getRole().name());
            statement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException ex1) {
            log.warn("Attempt to create existing user [email: {}]", user.getEmail());
            throw new UserAlreadyExistException("Such user already exists: " + user.getEmail());
        } catch (SQLException e) {
            log.error("ERROR: can't provide user save operation!", e);
            return Optional.empty();
        }
        return Optional.of(user);
    }

    @Override
    public Optional<User> update(User user) {
        if (Objects.isNull(user.getPassword()) || user.getPassword().isEmpty()) {
            return Optional.ofNullable(updateNoPass(user));
        }

        try (PreparedStatement statement =
                connection.prepareStatement(DBQueries.UPDATE_USER_QUERY)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getPassword());
            statement.setBoolean(4, user.isEnabled());
            statement.setString(5, user.getRole().name());
            statement.setLong(6, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("ERROR: can't provide user update operation!", e);
            return Optional.empty();
        }
        return Optional.of(user);
    }

    private User updateNoPass(User user) {
        try (PreparedStatement statement =
                     connection.prepareStatement(DBQueries.UPDATE_USER_SAME_PASSWORD_QUERY)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setBoolean(3, user.isEnabled());
            statement.setString(4, user.getRole().name());
            statement.setLong(5, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("ERROR: can't provide user update operation!", e);
            return new User();
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        try (PreparedStatement statement =
                connection.prepareStatement(DBQueries.FIND_ALL_USERS_QUERY,
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return userMapper.extractAll(rs);
            }
        } catch (SQLException e) {
            log.error("ERROR: can't provide user findAll operation!", e);
        }
        return new ArrayList<>();
    }

    @Override
    public Optional<User> delete(User user) {
        return Optional.empty();
    }
}
