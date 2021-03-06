package org.servlet.project.model.dao.mapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.servlet.project.exceptions.UserNotFoundException;
import org.servlet.project.model.entity.Activity;
import org.servlet.project.model.entity.Role;
import org.servlet.project.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserMapper implements ObjectMapper<User> {
    private static final Logger log = LogManager.getLogger(UserMapper.class);

    @Override
    public User extract(ResultSet rs) {
        User user = null;

        try {
            user = User.builder()
                    .id(rs.getLong("id"))
                    .firstName(rs.getString("first_name"))
                    .lastName(rs.getString("last_name"))
                    .email(rs.getString("email"))
                    .password(rs.getString("password"))
                    .enabled(rs.getBoolean("enabled"))
                    .role(Role.valueOf(rs.getString("role")))
                    .build();
        } catch (SQLException e) {
            log.error("User not found", e);
        }
        return user;
    }

    @Override
    public List<User> extractAll(ResultSet rs) throws SQLException {
        List<User> list = new ArrayList<>();
        rs.beforeFirst();

        while (rs.next()) {
            User user = extract(rs);
            list.add(user);
        }
        return list;
    }
}
