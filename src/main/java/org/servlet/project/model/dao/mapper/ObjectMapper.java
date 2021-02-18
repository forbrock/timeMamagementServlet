package org.servlet.project.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ObjectMapper<T> {
    T extract(ResultSet rs) throws SQLException;
}
