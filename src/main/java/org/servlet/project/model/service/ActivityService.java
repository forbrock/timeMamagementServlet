package org.servlet.project.model.service;

import org.servlet.project.model.dao.ActivityDao;
import org.servlet.project.model.dao.impl.DaoFactory;
import org.servlet.project.model.entity.Activity;

import java.util.List;
import java.util.Optional;

public class ActivityService {
    private final ActivityDao activityDao = DaoFactory.createActivityDao();

    public List<Activity> findAll() {
        return activityDao.findAll();
    }

    public Optional<Activity> findById(long id) {
        return activityDao.findById(id);
    }

    public Activity update(long id, String name) {
        return activityDao.update(Activity.builder()
                .id(id)
                .name(name)
                .build());
    }

    public Activity create(String name, long categoryId) {
        return activityDao.save(Activity.builder()
                .name(name)
                .categoryId(categoryId)
                .build());
    }
}
