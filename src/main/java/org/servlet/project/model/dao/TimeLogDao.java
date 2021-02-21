package org.servlet.project.model.dao;

import org.servlet.project.model.dto.TimeLogDto;

public interface TimeLogDao extends Dao<TimeLogDto> {

    boolean saveTime(String uaId, String time);
}
