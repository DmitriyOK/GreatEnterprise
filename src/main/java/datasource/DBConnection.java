package datasource;

import java.sql.*;


/**
 * Created by Dmitriy on 15.03.2017.
 */


public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/worktime";
    private static String username = "root";
    private static String password = "root";


    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(URL, username, password);
    }
}

