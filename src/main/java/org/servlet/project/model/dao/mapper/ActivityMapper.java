package org.servlet.project.model.dao.mapper;

import org.servlet.project.model.entity.Activity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActivityMapper implements ObjectMapper<Activity> {

    @Override
    public Activity extract(ResultSet rs) throws SQLException {
        return Activity.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .build();
    }

    @Override
    public List<Activity> extractAll(ResultSet rs) throws SQLException {
        List<Activity> list = new ArrayList<>();

        while (rs.next()) {
            Activity activity = extract(rs);
            list.add(activity);
        }
        return list;
    }
}
