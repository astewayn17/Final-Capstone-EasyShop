package org.yearup.data.mysql;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class MySqlDaoBase {

    // Data source property used to obtain a database connection.
    private DataSource dataSource;

    // Dao constructor that initializes the data source.
    public MySqlDaoBase(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Makes a connection object from the configured datasource.
    protected Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}