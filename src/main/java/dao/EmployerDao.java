package dao;

import model.Employer;

import java.util.List;

/**
 *  ДАО слой для {@link Employer} класса.
 *
 */
public interface EmployerDao {
    /**
     * Описание по ссылке {@link factory.AbstractEntityFactory}
     */
    Employer findByLogin(String login);

}
