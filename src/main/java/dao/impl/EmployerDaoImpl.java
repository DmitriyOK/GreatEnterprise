package dao.impl;

import dao.EmployerDao;
import factory.EntityFactory;
import model.Employer;
import sqlquery.SqlQuery;

import java.util.List;

/**
 * Реализация интерфейса {@link Employer}
 */
public class EmployerDaoImpl implements EmployerDao {

    private final EntityFactory ENTITY_FACTORY = new EntityFactory();

    public Employer findByLogin(String login) {
        return(Employer) ENTITY_FACTORY.findEmployerByLogin(login).get(0);
    }

    @Override
    public List<Object> findAllEmployersLogin() {
        return ENTITY_FACTORY.findAll(Employer.class,SqlQuery.FIND_EMPLOYERS_LOGIN.toString());
    }
}
