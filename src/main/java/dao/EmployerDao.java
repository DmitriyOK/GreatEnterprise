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

    /**
     * Находит в базе данных существующего пользователя
     * по указанному логину
     *
     * @return {@link Employer} или null если пользователь не существует
     */
    List<Object> findAllEmployersLogin();
}
