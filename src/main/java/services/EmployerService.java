package services;

import model.Employer;

/**
 * Сервис класс для {@link Employer}
 */
public interface EmployerService {

    Employer findByLogin(String login);
}
