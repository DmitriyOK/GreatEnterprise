package dao.impl;

import dao.EmployerDao;
import factory.EntityDb;
import model.Employer;
import sqlquery.SqlQuery;

/**
 * Created by Dmitriy on 16.03.2017.
 */
public class EmployerDaoImpl implements EmployerDao {
    public Employer findByLogin(String login) {
        return EntityDb.findEmployerByLogin(Employer.class, login);
    }
}
