package worktime.factory.impl;

import worktime.annotation.Column;
import worktime.annotation.Table;
import worktime.datasource.DBConnection;
import worktime.factory.AbstractEntityFactory;
import worktime.handler.ExceptionHandler;
import worktime.model.Employer;
import worktime.model.EmployerWorkDay;
import worktime.model.ReportCurrentDay;
import worktime.period.Period;
import worktime.sqlquery.SqlQuery;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;

import static worktime.period.Period.currentDayUnixTime;
import static worktime.period.Period.plusDay;

/**
 * Реализация интерфейса {@link AbstractEntityFactory}
 *
 * Класс, который представляет сущности баз данных в java - объекты.
 * Поля сопоставляются по аннотации {@link Column}.
 * Поддерживаются следующие типы данных: String, Integer, Boolean.
 * Если аннотацией отмечен не поддерживаемый тип данных,
 * значение переменной класса не будет присвоено.
 *
 * Обрабатывается {@link NoSuchElementException} когда у класса,
 *          отмеченного аннотацией {@link worktime.annotation.Entity}
 *          отсутсвует аннотация {@link worktime.annotation.Table}
 */
public class EntityFactory implements AbstractEntityFactory{

    public  List<Object> findEmployerByLogin(String login) {

        List<Object> resultList = null;
        try {
            String tableName = getTableName(Employer.class);
            Connection conn = DBConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(SqlQuery.FIND_BY_LOGIN.toString().replace(":tableName", tableName));
            preparedStatement.setString(1,login);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultList = findAllByResultSet(Employer.class, resultSet);

        } catch (Exception e) {
            ExceptionHandler.showToUser(e, true);
        }
        return resultList;
    }

    public  List<Object> findEmployerWorkDayHistoryByPeriod(Employer employer, int startPeriod, int endPeriod){

        List<Object> result=null;
        try{
            Connection conn =DBConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(SqlQuery.EMPLOYER_WORK_DAY_BY_PERIOD.toString());
            preparedStatement.setInt(1, startPeriod);
            preparedStatement.setInt(2, Period.plusDay(endPeriod));
            preparedStatement.setInt(3, employer.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            result = findAllByResultSet(EmployerWorkDay.class, resultSet);

        }
        catch (Exception e){
            ExceptionHandler.showToUser(e, true);
        }

        return result;
    }

    public  List<Object> findCurrentDayReport(){

        List<Object> result=null;
        try{
            Connection conn = DBConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(SqlQuery.CURRENT_DAY_REPORT.toString());
            preparedStatement.setInt(1, currentDayUnixTime(false));
            ResultSet resultSet = preparedStatement.executeQuery();
            result = findAllByResultSet(ReportCurrentDay.class, resultSet);
        }
        catch (Exception e){
            ExceptionHandler.showToUser(e, true);
        }
        return result;
    }

    public  List<Object> findSelectedDayReport(int selectedUnixTimeDay){

        List<Object> result=null;
        try{
            Connection conn=DBConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(SqlQuery.SELECTED_DAY_REPORT.toString());
            preparedStatement.setInt(1, selectedUnixTimeDay);
            preparedStatement.setInt(2, plusDay(selectedUnixTimeDay));
            ResultSet resultSet = preparedStatement.executeQuery();
            result = findAllByResultSet(ReportCurrentDay.class, resultSet);
        }
        catch (Exception e){
            ExceptionHandler.showToUser(e, true);
        }
        return result;
    }

    public  List<Object> findCurrentEmployerWorkDay(Employer employer) {

            List<Object> resultList = null;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement preparedStatment = conn.prepareStatement(SqlQuery.CURRENT_EMPLOYER_WORK_DAY.toString());
            preparedStatment.setInt(1, currentDayUnixTime(false));
            preparedStatment.setInt(2,employer.getId());
            ResultSet resultSet = preparedStatment.executeQuery();
            resultList = findAllByResultSet(EmployerWorkDay.class, resultSet);
        } catch (Exception e) {
            ExceptionHandler.showToUser(e, true);
        }
        return resultList;
    }

    public  EmployerWorkDay saveEmployerWorkDay(EmployerWorkDay employerWorkDay) {
        Connection conn;
        PreparedStatement preparedStatement;
        try {
            conn = DBConnection.getConnection();
            if (employerWorkDay.getFinishTime()==null) {
                preparedStatement = conn.prepareStatement(SqlQuery.UPDATE_EMPLOYER_WORK_DAY_BEGIN.toString());
                preparedStatement.setString(1, employerWorkDay.getStartTime());
                preparedStatement.setInt(2, employerWorkDay.getUnixStartTime());
                preparedStatement.setInt(3, employerWorkDay.getId());
                preparedStatement.executeUpdate();

            } else {
                preparedStatement = conn.prepareStatement(SqlQuery.UPDATE_EMPLOYER_WORK_DAY_END.toString());
                preparedStatement.setString(1, employerWorkDay.getFinishTime());
                preparedStatement.setInt(2, employerWorkDay.getUnixFinishTime());
                preparedStatement.setInt(3, employerWorkDay.getId());
                preparedStatement.executeUpdate();
            }

        } catch (Exception e){
            ExceptionHandler.showToUser(e, true);
        }

        return employerWorkDay;
    }

    public  EmployerWorkDay updateStatus(EmployerWorkDay employerWorkDay) {

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(SqlQuery.SET_STATUS.toString());
            preparedStatement.setBoolean(1, employerWorkDay.isOnline());
            preparedStatement.setInt(2, employerWorkDay.getId());
            preparedStatement.executeUpdate();

        } catch (Exception e){
            ExceptionHandler.showToUser(e, true);
        }

        return employerWorkDay;
    }

    private  List<Object> findAllByResultSet(Class aClass, ResultSet resultSet) {
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
                        if (Boolean.TYPE.isAssignableFrom(method.getParameterTypes()[0])) {
                            method.invoke(object, resultSet.getBoolean(method.getAnnotation(Column.class).name()));
                        }
                    }
                }
                objects.add(object);
            }
        } catch (Exception e){
            ExceptionHandler.showToUser(e, true);
        }
        finally {}
        return objects;
    }

    private  String getTableName(Class className) {
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


