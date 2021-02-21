package org.servlet.project.model.service;

import org.servlet.project.model.dao.TimeLogDao;
import org.servlet.project.model.dao.impl.DaoFactory;
import org.servlet.project.model.dto.TimeLogDto;

import java.util.List;

public class TimeLogService {
    private final TimeLogDao timeLogDao = DaoFactory.createTimeLogDao();

    List<TimeLogDto> findUserTimeLogs(long uaId) {
        return timeLogDao.findByUserActivityId(uaId);
    }

    public boolean addNewTimePoint(String uaId, String time) {
        return timeLogDao.saveTime(uaId, time);
    }
}
