package org.servlet.project.model.dao.impl;

import org.apache.commons.dbcp2.BasicDataSource;
import org.servlet.project.model.dao.DaoFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {
    private BasicDataSource dataSource = JDBCConnectionManager.getDataSource();

    public Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public JDBCUserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }

    @Override
    public JDBCUserActivityDao createUserActivityDao() {
        return new JDBCUserActivityDao(getConnection());
    }
}
