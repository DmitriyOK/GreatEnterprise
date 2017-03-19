package services.impl;

import validator.Validator;
import dao.EmployerDao;
import dao.impl.EmployerDaoImpl;
import model.Employer;
import services.EmployerService;

/**
 * Реализация интерфейса {@link EmployerService}
 */
public class EmployerServiceImpl implements EmployerService {

    EmployerDao employerDao = new EmployerDaoImpl();

    public Employer findByLogin(String login) {
        if(!Validator.isValidLogin(login)) {
            return null;
        }
        Employer employer = employerDao.findByLogin(login);
        return employer;
    }
}