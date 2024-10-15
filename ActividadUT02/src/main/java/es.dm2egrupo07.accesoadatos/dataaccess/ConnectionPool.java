package es.dm2egrupo07.accesoadatos.dataaccess;

import com.zaxxer.hikari.*;
import lombok.*;
import java.sql.*;

public class ConnectionPool {
    @Getter
    private static ConnectionPool instance = new ConnectionPool();

    private static final String CONNECTION_STRING = "jdbc:mariadb://localhost/classicmodels";
    private static final String USERNAME = "classicmodels";
    private static final String PASSWORD = "classicmodels";

    private HikariDataSource dataSource;

    private ConnectionPool() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(CONNECTION_STRING);
        config.setUsername(USERNAME);
        config.setPassword(PASSWORD);

        dataSource = new HikariDataSource(config);

    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
