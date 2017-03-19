package dao;

import model.Employer;

import java.util.List;

/**
 *  DAO слой для {@link Employer} класса.
 *
 */
public interface EmployerDao {
    /**
     * Описание по ссылке {@link factory.AbstractEntityFactory}
     * @param login
     * @return {@link Employer}
     */
    Employer findByLogin(String login);

}
