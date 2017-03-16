package datasource;



import annotation.Column;
import annotation.Table;
import com.mysql.jdbc.util.ResultSetUtil;
import model.Employer;
import sqlquery.SqlQuery;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.sql.*;
import java.util.*;

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

//


//    private static List<Employer> buildEmployerList(ResultSet resultSet) throws SQLException, InvocationTargetException, IllegalAccessException {
//
//        HashMap<String, Integer> rows = new HashMap<String, Integer>();
//        List<Employer> employers = new ArrayList<Employer>();
//        String fieldName = null;
//        for (Field field : employerClass.getDeclaredFields()) {
//            if (field.isAnnotationPresent(Column.class)) {
//                fieldName = field.getAnnotation(Column.class).name();
//                rows.put("set" + fieldName.toLowerCase(), resultSet.findColumn(fieldName));
//            }
//        }
//        while (resultSet.next()) {
//            Employer employer = new Employer();
//            for (Method method : employerClass.getDeclaredMethods()) {
//                String rowKey = method.getName().toLowerCase();
//                if (rows.containsKey(rowKey)) {
//                    if (String.class.isAssignableFrom(method.getParameterTypes()[0])) {
//                        method.invoke(employer, resultSet.getString(rows.get(rowKey)));
//                    }
//                    if (Integer.TYPE.isAssignableFrom(method.getParameterTypes()[0])) {
//                        method.invoke(employer, resultSet.getInt(rows.get(rowKey)));
//                    }
//                }
//            }
//            employers.add(employer);
//        }
//        System.out.println(employers);
//        return employers;
//    }

    public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(URL, username, password);
    }
    private static void showColumnsName(ResultSet resultSet) throws SQLException {
        ResultSetMetaData meta = resultSet.getMetaData();
        for (int i = 1; i <= meta.getColumnCount(); i++) {
            System.out.println(meta.getColumnName(i));
            System.out.println(resultSet.findColumn(meta.getColumnName(i)));
        }
    }
}

