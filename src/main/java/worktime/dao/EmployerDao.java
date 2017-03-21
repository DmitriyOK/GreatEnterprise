package worktime.dao;

import worktime.model.Employer;

/**
 *  DAO слой для {@link Employer} класса.
 *
 */
public interface EmployerDao {
    /**
     * Описание по ссылке {@link worktime.factory.AbstractEntityFactory}
     * @param login
     * @return {@link Employer}
     */
    Employer findByLogin(String login);

}
