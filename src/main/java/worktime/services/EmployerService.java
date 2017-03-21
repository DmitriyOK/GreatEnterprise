package worktime.services;

import worktime.model.Employer;

/**
 * Сервис класс для {@link Employer}
 */
public interface EmployerService {

    /**
     *  Возвращает текущего сотрудника по заданному логину.
     *
     * @param login заданный логин
     * @return {@link Employer} текущий сотрудник
     */
    Employer findByLogin(String login);
}
