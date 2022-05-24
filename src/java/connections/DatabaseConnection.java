package connections;

import exceptions.DatabaseException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DatabaseConnection {

    private static Connection connection;

    private static Properties loadProperties() {
        try (FileInputStream fs = new FileInputStream("src/resources/application.properties")) {
            Properties properties = new Properties();
            properties.load(fs);
            return properties;
        } catch (IOException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public static Connection startConnection() {
        if (connection == null) {
            String url = loadProperties().getProperty("dburl");
            try {
                connection = DriverManager.getConnection(url, loadProperties());
            } catch (SQLException e) {
                throw new DatabaseException(e.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DatabaseException(e.getMessage());
            }
        }
    }

    public static void statementClose(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public static void resultSetClose(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
