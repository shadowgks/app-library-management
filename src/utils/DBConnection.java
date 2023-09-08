package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/library_app";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private static Connection connection;

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return connection;
    }
}
