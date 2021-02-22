package org.servlet.project.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ObjectMapper<T> {
    T extract(ResultSet rs) throws SQLException;
    List<T> extractAll(ResultSet rs) throws SQLException;
}
