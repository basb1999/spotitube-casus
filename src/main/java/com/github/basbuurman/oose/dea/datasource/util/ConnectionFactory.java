package com.github.basbuurman.oose.dea.datasource.util;

import com.github.basbuurman.oose.dea.services.exceptions.RequestInvalidException;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private DatabaseProperties databaseProperties;

    @Inject
    public void setDatabaseProperties(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }

    public Connection createConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(connectionString());
        } catch (SQLException e) {
            throw new RequestInvalidException();
        }

        return connection;
    }

    public String connectionString() {
        return databaseProperties.connectionString();
    }
}