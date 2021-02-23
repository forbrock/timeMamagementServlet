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
            throw new RuntimeException(e);
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
            log.warn("User not found: {}", email);
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public User save(User user) {
        try (PreparedStatement statement =
                connection.prepareStatement(DBQueries.SAVE_USER_QUERY)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException ex1) {
            log.warn("Attempt to create existing user [email: {}]", user.getEmail());
            throw new UserAlreadyExistException("Such user already exists: " + user.getEmail());
        } catch (SQLException e) {
            log.error("ERROR: can't provide save operation!");
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        try (PreparedStatement statement =
                connection.prepareStatement(DBQueries.FIND_ALL_USERS,
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return userMapper.extractAll(rs);
            }
        } catch (SQLException e) {
            log.error("ERROR [method findAll()]: can not provide operation!");
        }
        return new ArrayList<>();
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public User delete(User user) {
        return null;
    }
}
