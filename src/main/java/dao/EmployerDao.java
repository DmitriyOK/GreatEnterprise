package dao;

import model.Employer;

import java.util.List;

/**
 * Created by Dmitriy on 16.03.2017.
 */
public interface EmployerDao {
    Employer findByLogin(String login);
    List<Object> findAllEmployersLogin();
}
