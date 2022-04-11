package io.deeplay.lab;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class PGConnector {

    public static Connection getConnection(String db_url, String user, String pass) {
        System.out.println("Testing connection to PostgreSQL JDBC");

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(db_url, user, pass);

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return null;
        }

        if (connection != null) {
            System.out.println("You successfully connected to database now");
        } else {
            System.out.println("Failed to make connection to database");
        }
        return connection;
    }
}
