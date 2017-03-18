package factory;

import annotation.Column;
import annotation.Table;
import datasource.DBConnection;
import model.Employer;
import model.EmployerWorkDay;
import model.ReportCurrentDay;
import sqlquery.SqlQuery;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;

import static period.Period.currentDay;

public abstract class EntityFactory {

    public boolean validationQuery(){
    return true;
    }

    public static List<Object> findAll(Class aClass, String query) {
        return findAllByResultSet(aClass, findEntity(aClass, null));
    }

    public static List<Object> findEmployerByLogin(String login) {

        ResultSet resultSet;
        Connection conn;
        PreparedStatement preparedStatement;
        String tableName = getTableName(Employer.class);
        List<Object> resultList = null;
        try {
            conn = DBConnection.getConnection();
            preparedStatement = conn.prepareStatement(SqlQuery.FIND_BY_LOGIN.toString().replace(":tableName", tableName));
            preparedStatement.setString(1,login);
            resultSet = preparedStatement.executeQuery();
            resultList = findAllByResultSet(Employer.class, resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }

    public static List<Object> findEmployerWorkDayHistoryByPeriod(Employer employer, int startPeriod, int endPeriod){

        Connection conn;
        PreparedStatement preparedStatement;
        List<Object> result=null;

        try{
            conn=DBConnection.getConnection();
            preparedStatement = conn.prepareStatement(SqlQuery.EMPLOYER_WORK_DAY_BY_PERIOD.toString());
            preparedStatement.setInt(1, startPeriod);
            preparedStatement.setInt(2, endPeriod);
            preparedStatement.setInt(3, employer.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            result = findAllByResultSet(EmployerWorkDay.class, resultSet);
        }
        catch (Exception e){}

        return result;
    }

    public static List<Object> findCurrentDayReport(){
        Connection conn;
        PreparedStatement preparedStatement;
        List<Object> result=null;
        try{
            conn=DBConnection.getConnection();
            preparedStatement = conn.prepareStatement(SqlQuery.CURRENT_DAY_REPORT.toString());
            preparedStatement.setInt(1, currentDay());
            ResultSet resultSet = preparedStatement.executeQuery();
            result = findAllByResultSet(ReportCurrentDay.class, resultSet);
        }
        catch (Exception e){e.printStackTrace();}
        return result;
    }

    public static List<Object> findCurrentEmployerWorkDay(Employer employer) {
            Connection conn;
            PreparedStatement preparedStatment;
            ResultSet resultSet;
            List<Object> resultList = null;
        try {
            conn = DBConnection.getConnection();
            preparedStatment = conn.prepareStatement(SqlQuery.CURRENT_EMPLOYER_WORK_DAY.toString());
            preparedStatment.setInt(1, currentDay());
            preparedStatment.setInt(2,employer.getId());
            resultSet = preparedStatment.executeQuery();
            resultList = findAllByResultSet(EmployerWorkDay.class, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public static EmployerWorkDay saveEmployerWorkDay(EmployerWorkDay employerWorkDay) {
        Connection conn;
        PreparedStatement preparedStatement;
        try {
            conn = DBConnection.getConnection();
            if (!employerWorkDay.isOnline()) {
                preparedStatement = conn.prepareStatement(SqlQuery.UPDATE_EMPLOYER_WORK_DAY_BEGIN.toString());
                preparedStatement.setString(1, employerWorkDay.getStartTime());
                preparedStatement.setInt(2, employerWorkDay.getUnixStartTime());
                preparedStatement.setBoolean(3, true);
                preparedStatement.setInt(4, employerWorkDay.getId());
                preparedStatement.executeUpdate();
                employerWorkDay.setOnline(true);

            } else {
                preparedStatement = conn.prepareStatement(SqlQuery.UPDATE_EMPLOYER_WORK_DAY_END.toString());
                preparedStatement.setString(1, employerWorkDay.getFinishTime());
                preparedStatement.setInt(2, employerWorkDay.getUnixFinishTime());
                preparedStatement.setBoolean(3, false);
                preparedStatement.setInt(4, employerWorkDay.getId());
                preparedStatement.executeUpdate();
                employerWorkDay.setOnline(false);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return employerWorkDay;
    }

    private static ResultSet findEntity(Class className, String query) {

        PreparedStatement preparedStatement;
        Connection conn;
        ResultSet result=null;
        try {
            String tableName = getTableName(className);
            if(query == null) {
                query = SqlQuery.ALL.toString().replace("?", tableName);
                conn = DBConnection.getConnection();
                preparedStatement = conn.prepareStatement(query);
                result=preparedStatement.executeQuery();
            }
            else {
                conn = DBConnection.getConnection();
                preparedStatement = conn.prepareStatement(query);
                result=preparedStatement.executeQuery();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    private static List<Object> findAllByResultSet(Class aClass, ResultSet resultSet) {
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


