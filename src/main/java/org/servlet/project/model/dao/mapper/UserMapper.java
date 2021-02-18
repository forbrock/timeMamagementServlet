package org.servlet.project.model.dao.mapper;

import org.servlet.project.exception.UserNotFoundException;
import org.servlet.project.model.entity.Role;
import org.servlet.project.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper implements ObjectMapper<User> {

    @Override
    public User extract(ResultSet rs) {
        User user = null;

        try {
            if (!rs.next()) {
                throw new UserNotFoundException("User not found");
            }
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
            // TODO: logger and handle exception
        }
        return user;
    }
}
