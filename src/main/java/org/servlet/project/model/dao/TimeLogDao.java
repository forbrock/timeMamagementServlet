package org.servlet.project.model.dao;

import org.servlet.project.model.dto.TimeLogDto;

import java.util.List;

public interface TimeLogDao extends Dao<TimeLogDto> {
    List<TimeLogDto>findByUserActivityId(long uaId);
    boolean saveTime(String uaId, String time);
}
