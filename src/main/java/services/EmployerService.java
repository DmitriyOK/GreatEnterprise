package services;

import model.Employer;

/**
 * Created by Dmitriy on 16.03.2017.
 */
public interface EmployerService {

    Employer findByLogin(String login);
}
