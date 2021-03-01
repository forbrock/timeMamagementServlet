package org.servlet.project.model.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    Optional<T> findById(long id);
    List<T> findAll();
    Optional<T> save(T t);
    Optional<T> update(T t);
    Optional<T> delete(T t);
}
