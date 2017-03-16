package factory;

import annotation.Column;
import annotation.Table;
import com.sun.istack.internal.Nullable;

import datasource.DBConnection;
import model.Employer;
import model.EmployerWorkDay;
import sqlquery.SqlQuery;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Dmitriy on 16.03.2017.
 */
public class EntityDb {

    public static void main(String[] args) throws SQLException, InvocationTargetException, IllegalAccessException, InstantiationException, ParseException, InterruptedException {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        EmployerWorkDay newDay = new EmployerWorkDay();
//        java.util.Date date = new java.util.Date();
//        newDay.setEmployerId(1);
//        EmployerWorkDayDaoImpl dayDao = new EmployerWorkDayDaoImpl();
//        ArrayList<EmployerWorkDay> result = (ArrayList) dayDao.findAll();
//        for(EmployerWorkDay emp: result)
//            System.out.println(emp);




//        newDay.setStartTime(format.format(date));
//        newDay.setUnixStartTime((int)(date.getTime()/1000));
//        EntityDb.saveEntity(newDay);
//
//        Thread.sleep(10000);
//        date=new java.util.Date();
//        newDay.setFinishTime(format.format(date));
//        newDay.setUnixFinishTime((int)(date.getTime()/1000));
//        EntityDb.saveEntity(newDay);
//
//        System.out.println(newDay);
        ResultSet rs = EntityDb.findEntity(Employer.class,null);
        List<Object> allByResultSet = EntityDb.findAllByResultSet(Employer.class, rs);
        System.out.println(allByResultSet);

    }

    public static Employer findEmployerById(Class<Employer> employerClass, String login) {
    return null;
    }

    /*ГОТОВЫЕ МЕТОДЫ*/

    public static EmployerWorkDay saveEntity(EmployerWorkDay employerWorkDay) {

        Connection conn;
        PreparedStatement preparedStatement;
        try {
            conn = DBConnection.getConnection();
            if (employerWorkDay.getId() == null) {
                preparedStatement = conn.prepareStatement(SqlQuery.INSERT_NEW_EMPOYER_WORK_DAY.toString(),
                        Statement.RETURN_GENERATED_KEYS);

                preparedStatement.setInt(1, employerWorkDay.getEmployerId());
                preparedStatement.setString(2, employerWorkDay.getStartTime());
                preparedStatement.setInt(3, employerWorkDay.getUnixStartTime());
                preparedStatement.executeUpdate();

                ResultSet rs = preparedStatement.getGeneratedKeys();
                if(rs.next()) {
                    employerWorkDay.setId(rs.getInt(1));
                }
            } else {
                preparedStatement = conn.prepareStatement(SqlQuery.UPDATE_EMPLOYER_WORK_DAY.toString());
                preparedStatement.setString(1,employerWorkDay.getFinishTime());
                preparedStatement.setInt(2,employerWorkDay.getUnixFinishTime());
                preparedStatement.setInt(3, employerWorkDay.getId());
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e){
            System.out.println("EntityDb");
            e.printStackTrace();
        }

        return employerWorkDay;
    }

    public static ResultSet findEntity(Class className, @Nullable String query) {

        PreparedStatement preparedStatement;
        Connection conn;
        ResultSet result=null;
        try {
            String tableName = getTableName(className);
            if(query == null) {
                query = SqlQuery.ALL.toString().replace(":tableName", tableName);
            }
             conn = DBConnection.getConnection();
             preparedStatement = conn.prepareStatement(query);
             result=preparedStatement.executeQuery();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public static List<Object> findAllByResultSet(Class aClass, ResultSet resultSet) {
        List<Object> objects = new ArrayList<Object>();
        try {
            while (resultSet.next()) {
                Object object = aClass.newInstance();
                for (Method method : aClass.getDeclaredMethods()) {
                    if (method.isAnnotationPresent(Column.class)) {
                        if (String.class.isAssignableFrom(method.getParameterTypes()[0])) {
                            method.invoke(object, resultSet.getString(method.getAnnotation(Column.class).name()));
                        }
                        if (Integer.TYPE.isAssignableFrom(method.getParameterTypes()[0])) {
                            method.invoke(object, resultSet.getInt(method.getAnnotation(Column.class).name()));
                        }
                    }
                }
                objects.add(object);
            }
        } catch (Exception e){
           e.printStackTrace();
        }
        finally {}
        return objects;
    }

    private static String getTableName(Class className) {
        String tableName = null;
        for (Annotation annotation : className.getDeclaredAnnotations()) {
            if (annotation instanceof Table)
                tableName = ((Table) annotation).name();
        }
        if (tableName == null) {
            throw new NoSuchElementException(String.format("%s class is not present %s", className.getName(), Table.class.getName()));
        }
        return tableName;
    }

}

