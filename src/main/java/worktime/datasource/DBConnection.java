package worktime.datasource;

import worktime.handler.ExceptionHandler;
import worktime.validator.Validator;
import worktime.main.Application;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
    private static EntityManager entityManager;

    /**
     * Инициализирует соединение с базой данных из загружаемого
     * *.properties файла. {@link Properties} и сохраняет на диск
     * для последующего использования.
     * После установки соединения выполняется тестовый SELECT - запрос.
     * и, если успешно, устанавливает переменную isInit - true.

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

                Application.printText("Соединение с базой данных установлено. Авторизуйтесь");
                saveFileIsNotExist();
                isInit=true;
            } catch (Exception e) {
                ExceptionHandler.showToUser(e, false);
                return isInit;
            }

        return true;
    }

    private static void saveFileIsNotExist() {

        if(!new File("config.properties").exists()) {
            try {
                properties.store(new FileOutputStream("config.properties"), null);
                Application.printText("Файл настроек сохранен");
            } catch (IOException e) {
                ExceptionHandler.showToUser(e, false);
            }
        }
    }

    /**
     * Загружает настройки базы данных из ранее сохраненного файла.
     * Перед вызовом метода требуется вызвать {@link #init(File propertiesFile)}
     */
    public static void init()  {
        try {
            DBConnection.init(new File("config.properties"));
        } catch (Exception e) {
            ExceptionHandler.showToUser(e, false);
        }
    }

    /**
     * Возвращает true если класс инициализирован.
     *
     * @return {@link Connection} текущее состояние
     */
    public static boolean isInit() {
        return isInit;
    }

    /**
     *  Возврщает текущее соединение
     *
     *  @return {@link Connection} соединение
     */
    public static Connection getConnection(){

        return connection;
    }

    public static EntityManager getEntityManager() {
        if (entityManager == null){
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpaUnit");
            entityManager = factory.createEntityManager();
        }
        return entityManager;
    }
}

