package org.servlet.project.model.service;

import org.servlet.project.model.dao.TimeLogDao;
import org.servlet.project.model.dao.impl.DaoFactory;

public class TimeLogService {
    private final TimeLogDao timeLogDao = DaoFactory.createTimeLogDao();

    public boolean addNewTimePoint(String uaId, String time) {
        return timeLogDao.saveTime(uaId, time);
    }
}
