package datasource;

import factory.EntityFactory;

import java.io.*;
import java.sql.*;
import java.util.Properties;


/**
 * Класс для получения соединения с базой данных.
 */

public abstract class DBConnection {

    private static Connection connection;
    private static Properties properties = new Properties();
    private static  String dataBaseUrl, username, password;
    private static boolean isInit;

    /**
     * Получает подключение к базе данных
     *
     * @return {@link Connection}
     * @throws SQLException
     */

    public static boolean init() {
        if (!isInit) {
            try {
                properties.load(new FileInputStream("config.properties"));
                Class.forName(properties.getProperty("driverClassName"));
                dataBaseUrl = properties.getProperty("dataBaseUrl");
                username = properties.getProperty("username");
                password = properties.getProperty("password");
                connection = DriverManager.getConnection(dataBaseUrl, username, password);
                connection.createStatement().execute("SELECT 1");
                isInit = true;
            } catch (Exception e) {
            }
        }
        return isInit;
    }

    public static Connection getConnection() throws SQLException {
        return connection;
        }
}

