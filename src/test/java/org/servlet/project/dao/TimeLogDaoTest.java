package org.servlet.project.dao;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.servlet.project.model.dao.TimeLogDao;
import org.servlet.project.model.dao.impl.DaoFactory;
import org.servlet.project.model.dto.TimeLogDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TimeLogDaoTest {
    private static DaoFactory daoFactory;
    private static TimeLogDao timeLogDao;

    @BeforeAll
    public static void init() {
        daoFactory = new DaoFactory();
        timeLogDao = daoFactory.createTimeLogDao();
    }

    @Test
    public void findByUserActivityId_test() {
        List<TimeLogDto> list = timeLogDao.findByUserActivityId(2);
        assertNotNull(list);
    }
}
