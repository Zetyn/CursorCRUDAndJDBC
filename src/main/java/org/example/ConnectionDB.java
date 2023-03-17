package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class ConnectionDB {
    private static final Properties properties;

    static {
        properties = new Properties();
        try (InputStream inputStream = new FileInputStream("configDB.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new ExceptionInInitializerError("Failed to load config file.");
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = properties.getProperty("db.url");
        String username = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");

        return DriverManager.getConnection(url, username, password);
    }

}
