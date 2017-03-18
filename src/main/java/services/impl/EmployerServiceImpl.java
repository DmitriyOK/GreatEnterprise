package services.impl;

import Validator.Validator;
import dao.EmployerDao;
import dao.impl.EmployerDaoImpl;
import model.Employer;
import services.EmployerService;

/**
 * Created by Dmitriy on 16.03.2017.
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