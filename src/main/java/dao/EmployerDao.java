package dao;

import model.Employer;

/**
 * Created by Dmitriy on 16.03.2017.
 */
public interface EmployerDao {
    Employer findByLogin(String login);
}
