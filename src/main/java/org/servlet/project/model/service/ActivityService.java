package org.servlet.project.model.service;

import org.servlet.project.model.dao.ActivityDao;
import org.servlet.project.model.dao.impl.DaoFactory;
import org.servlet.project.model.entity.Activity;

import java.util.List;

public class ActivityService {
    private final ActivityDao activityDao = DaoFactory.createActivityDao();

    public List<Activity> findAll() {
        return activityDao.findAll();
    }
}
