package dao.impl;

import dao.EmployerDao;
import factory.EntityFactory;
import model.Employer;
import sqlquery.SqlQuery;

import java.util.List;

/**
 * Created by Dmitriy on 16.03.2017.
 */
public class EmployerDaoImpl implements EmployerDao {
    public Employer findByLogin(String login) {
        return(Employer) EntityFactory.findEmployerByLogin(login).get(0);
    }

    @Override
    public List<Object> findAllEmployersLogin() {
        return EntityFactory.findAll(Employer.class,SqlQuery.FIND_EMPLOYERS_LOGIN.toString());
    }
}
