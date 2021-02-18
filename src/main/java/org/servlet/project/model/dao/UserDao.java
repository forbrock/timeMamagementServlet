package org.servlet.project.model.dao;

import org.servlet.project.model.entity.User;

import java.util.Optional;

public interface UserDao extends Dao<User> {
    Optional<User> findByEmail(String email);
}
