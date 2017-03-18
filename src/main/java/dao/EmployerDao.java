package dao;

import model.Employer;

import java.util.List;

/**
 *  ДАО слой для {@link Employer} класса.
 *
 */
public interface EmployerDao {
    /**
     * Находит существующего пользователя по указанному логину
     *
     * @param login - логин пользователя
     * @return {@link Employer} или null если пользователь не существует
     */
    Employer findByLogin(String login);

}
