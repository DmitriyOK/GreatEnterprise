package factory;



import model.Employer;
import model.EmployerWorkDay;
import model.ReportCurrentDay;

import java.util.List;

/**
 *  Главниый интерфейс для объектного представления
 *  сущностей таблиц баз данных. Поддерживаемые POJO - классы
 *  указаны в документации реализующего класса {@link factory.impl.EntityFactory }
 */

public interface AbstractEntityFactory {

    /**
     * Находит существующего пользователя по указанному логину
     *
     * @param login - логин пользователя
     * @return {@link Employer} или null если пользователь не существует
     */

      List<Object> findEmployerByLogin(String login);

    /** Возвращает отчет по заданному сотруднку за указанный период
     *
     * @param employer - заданный сотрудник
     * @param startPeriod - начало периода включительно
     * @param endPeriod - конец периода включительно
     * @return {@link List} отчет по заданному сотруднку за указанный период
     */

      List<Object> findEmployerWorkDayHistoryByPeriod(Employer employer, int startPeriod, int endPeriod);

    /**
     * Возвращает отчет за текущий день.
     * Выбранный период - 24 часа с начала календарного дня.
     *
     * @return {@link ReportCurrentDay} - отчет за текущий день.
     */

      List<Object> findCurrentDayReport();

    /**
     * Возвращает отчет за заданный день.
     * Указывается только один параметр - отчетный день,
     * который представляет 24 часовой период с начала дня
     *
     * @param selectedUnixTimeDay - заданный период в секундах
     * @return {@link List} объектов
     */

      List<Object> findSelectedDayReport(int selectedUnixTimeDay);

    /**
     * Возвращает текущий рабочий день для авторизованного сотрудника
     * Используется для манипуляции с рабочим днем в течение дня
     *
     * @param employer - авторизованный сотрудник
     * @return {@link EmployerWorkDay] - текущий рабочий день.
     */

      List<Object> findCurrentEmployerWorkDay(Employer employer);

    /**
     * Сохраняет внесенные в рабочий день изменения для текущего сотрудника
     *
     * @param employerWorkDay - актульное состояние объекта
     * @return {@link EmployerWorkDay} - измененный объект
     */

      EmployerWorkDay saveEmployerWorkDay(EmployerWorkDay employerWorkDay);

    /**
     * Обновляет статус сотрудника isOnline.
     *
     * @param employerWorkDay - актуальный состояние объекта
     * @return {@link EmployerWorkDay} измененный объект
     */

      EmployerWorkDay updateStatus(EmployerWorkDay employerWorkDay);

}
