package dao.impl;

import dao.EmployerDao;
import factory.impl.EntityFactory;
import model.Employer;

/**
 * Реализация интерфейса {@link Employer}
 */
public class EmployerDaoImpl implements EmployerDao {

    private final EntityFactory ENTITY_FACTORY = new EntityFactory();

    public Employer findByLogin(String login) {
        return(Employer) ENTITY_FACTORY.findEmployerByLogin(login).get(0);
    }

}
