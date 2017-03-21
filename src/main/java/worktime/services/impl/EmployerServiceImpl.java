package worktime.services.impl;

import worktime.validator.Validator;
import worktime.dao.EmployerDao;
import worktime.dao.impl.EmployerDaoImpl;
import worktime.model.Employer;
import worktime.services.EmployerService;

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