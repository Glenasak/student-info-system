package com.cjlu.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.dbcp2.BasicDataSource;

public class JDBCUtils {
    // Database connection pool instance
    private static BasicDataSource dataSource;

    // Configure the connection pool
    static {
        dataSource = new BasicDataSource();
        // Supply the driver class name (core fix)
        // Newer Derby JARs register the driver via ServiceLoader, and the implementation class is AutoloadedDriver
        dataSource.setDriverClassName("org.apache.derby.iapi.jdbc.AutoloadedDriver");
        // Existing URL, username, and password configuration
        dataSource.setUrl("jdbc:derby:MyDB;create=true");
        dataSource.setUsername("Admin");
        dataSource.setPassword("123456");
        // Connection pool parameters (note: in DBCP 1.x, maxActive represents the maximum number of connections and does not need changes)
        dataSource.setMaxIdle(5);
        dataSource.setMaxIdle(5);
        dataSource.setMinIdle(2);
        dataSource.setMaxWaitMillis(5000);
    }

    // Method that retrieves a database connection
    public static Connection getConnection() throws Exception {
        return dataSource.getConnection();
    }

    // Return resources to the pool
    public static void closeResources(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        // Close the result set
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Close the prepared statement
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Close the connection object
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Close statement and connection when no result set is involved
    public static void closeResources(Connection conn, PreparedStatement pstmt) {
        closeResources(conn, pstmt, null);
    }
}