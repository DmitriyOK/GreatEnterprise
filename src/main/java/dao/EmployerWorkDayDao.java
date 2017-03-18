package dao;


import model.Employer;
import model.EmployerWorkDay;
import model.ReportCurrentDay;

import java.util.List;

/**
 * DAO слой для работы с основной таблицей.
 */
public interface EmployerWorkDayDao {

    /** Возвращает отчет по заданному сотруднку за указанный период
     *
     * @param employer - заданный сотрудник
     * @param startPeriod - начало периода включительно
     * @param endPeriod - конец периода включительно
     * @return {@link List} отчет по заданному сотруднку за указанный период
     */
    List<EmployerWorkDay> findEmployerWorkDayByPeriod(Employer employer, int startPeriod, int endPeriod);

    /**
     * Возвращает отчет за заданный день.
     * Указывается только один параметр - отчетный день,
     * который представляет 24 часовой период с начала дня
     *
     * @param selectedUnixTimeDay - заданный период в секундах
     * @return
     */
    List<ReportCurrentDay> findBySelectedPeriod(int selectedUnixTimeDay);

    /**
     * Выводит последние 1000 отработанных дней.
     * @return {@link}
     */
    List<EmployerWorkDay> findAll();

    /**
     * Возвращает текущий рабочий день для авторизованного сотрудника
     * Используется для манипуляции с рабочим днем в течение дня
     *
     * @param employer - авторизованный сотрудник
     * @return {@link EmployerWorkDay] - текущий рабочий день.
     */
    EmployerWorkDay findCurrentEmployerWorkDay(Employer employer);

    /**
     * Сохраняет внесенные в рабочий день изменения для текущего сотрудника
     *
     * @param employerWorkDay - актульное состояние объекта
     * @return {@link EmployerWorkDay} - измененный объект
     */
    EmployerWorkDay save(EmployerWorkDay employerWorkDay);

    /**
     * Обновляет статус сотрудника isOnline.
     *
     * @param employerWorkDay - актуальный состояние объекта
     *
     * @return {@link EmployerWorkDay} измененный объект
     */
    EmployerWorkDay updateStatus(EmployerWorkDay employerWorkDay);

    /**
     * Возвращает отчет за текущий день.
     * Выбранный период - 24 часа с начала календарного дня.
     *
     * @return {@link ReportCurrentDay} - отчет за текущий день.
     */
    List<ReportCurrentDay> findCurrentDayReport();
}

