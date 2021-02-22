package org.servlet.project.model.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    Optional<T> findById(long id);
    List<T> findAll();
    T save(T t);
    T update(T t);
    T delete(T t);
}
