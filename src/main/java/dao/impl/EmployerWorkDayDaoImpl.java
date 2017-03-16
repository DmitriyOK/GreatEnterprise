package dao.impl;



import com.sun.istack.internal.Nullable;
import dao.EmployerWorkDayDao;
import datasource.DBConnection;
import model.Employer;
import model.EmployerWorkDay;
import sqlquery.SqlQuery;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import factory.EntityDb;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitriy on 16.03.2017.
 */
public class EmployerWorkDayDaoImpl implements EmployerWorkDayDao {

    //TODO затестить
    public List<EmployerWorkDay> findEmployerWorkDayByPeriod(@Nullable Employer employer, int startPeriod, int endPeriod) {
        String query =String.format(SqlQuery.EMPLOYER_WORK_DAY_BY_PERIOD.toString(), startPeriod, endPeriod);
        ResultSet resultSet = EntityDb.findEntity(EmployerWorkDay.class, query);
        List<Object> listObjects = EntityDb.findAllByResultSet(EmployerWorkDay.class, resultSet);
        return convertToArrayList(listObjects);
    }

    public List<EmployerWorkDay> findAll(){
        ResultSet resultSet = EntityDb.findEntity(EmployerWorkDay.class, null);
        List<Object> listObjects = EntityDb.findAllByResultSet(EmployerWorkDay.class, resultSet);
        return convertToArrayList(listObjects);
    }

    public EmployerWorkDay save(EmployerWorkDay employerWorkDay) {
        EntityDb.saveEntity(employerWorkDay);
        return null;
    }

    private ArrayList<EmployerWorkDay> convertToArrayList(List listObjects){
        ArrayList<EmployerWorkDay> result = new ArrayList<EmployerWorkDay>();
        for (Object object : listObjects) {
            result.add((EmployerWorkDay) object);
        }
        return result;
    }
}
