package datasource;

import validator.Validator;
import main.Application;

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
     * @return {@link Connection} текущее соединение
     */
    public static Connection getConnection() {
        return connection;
        }

    /**
     * Инициализирует соединение с базой данных из загружаемого
     * *.properties файла. {@link Properties} Исполняет тестовый запрос
     * и, если успешно, устанавливает переменную isInit - true.
     * При запуске программы соединение отстусвует, нужно загрузить
     * *.properties - файл для установки соединения.
     *
     * Имена параметров *.properties файла:
     *
     * password = пароль
     * username = имя пользователя
     * driverClassName = класс драйвера
     * dataBaseUrl = адрес соединения
     *
     * @param propertiesFile принимает путь к *.properties - файлу.
     * @return true или false как результат инициализации.
     */
    public static boolean init(File propertiesFile){
            try {
                if(propertiesFile == null || !Validator.isPropertiesFile(propertiesFile.getAbsolutePath())){
                    Application.printText("Предоставленный файл не является *.properties");
                    return false;
                }

                if(propertiesFile != null && connection != null){
                    connection.close();
                }
                properties.load(new FileInputStream(propertiesFile));
                Class.forName(properties.getProperty("driverClassName"));
                dataBaseUrl = properties.getProperty("dataBaseUrl");
                username = properties.getProperty("username");
                password = properties.getProperty("password");
                connection = DriverManager.getConnection(dataBaseUrl, username, password);
                connection.createStatement().execute("SELECT 1");
                isInit=true;
            } catch (Exception e) {
                return isInit;
            }

        return true;
    }

    public static boolean isInit() {
        return isInit;
    }
}

