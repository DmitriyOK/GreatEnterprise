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

import javax.management.AttributeList;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitriy on 16.03.2017.
 */
public class EmployerWorkDayDaoImpl implements EmployerWorkDayDao {

    //TODO затестить
    public List<EmployerWorkDay> findEmployerWorkDayByPeriod(Employer employer, int startPeriod, int endPeriod) {
        List<Object> listObjects = EntityDb.findEmployerWorkDayHistoryByPeriod(employer, startPeriod, endPeriod);
        return convertToArrayList(listObjects);
    }

    public List<EmployerWorkDay> findAll(){
        ResultSet resultSet = EntityDb.findEntity(EmployerWorkDay.class, null);
        List<Object> listObjects = EntityDb.findAllByResultSet(EmployerWorkDay.class, resultSet);
        return convertToArrayList(listObjects);
    }

    @Override
    public EmployerWorkDay findByLogin(String login) {
        return null; //TODO сделать
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
