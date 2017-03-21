package worktime.sqlquery;

import worktime.model.Employer;
import worktime.model.EmployerWorkDay;
import worktime.services.EmployerWorkDayService;

/**
 * Используемые запросы во время работы программы
 */
public enum SqlQuery {
    /**
     *  Возвращает отчет за ТЕКУЩИЙ рабочий день как результат выборки из двух таблиц.
     *  Результативная таблица содержит 5 столбцов:
     *  логин сотрудника, начало и окончание рабочего дня в обычном и unix time представлении, статус.
     *
     *  Сортируется по убыванию unixStartTime
     *  работники, начавшие ТЕКУЩИЙ рабочий день позднее всех, отобразятся на первых позициях отчета
     *
     *  {@link EmployerWorkDayService#findCurrentDayReport()}
     */
    CURRENT_DAY_REPORT("SELECT  login, startTime, finishTime, unixStartTime, unixFinishTime, isOnLine "+
    "FROM employerworkday "+
    "JOIN employer ON employer.id = employerworkday.employerId "+
    "WHERE unixStartTime >=? "+
    "ORDER BY unixStartTime DESC"),

    /**
     *  Возвращает отчет за ЗАДАННЫЙ как результат выборки из двух таблиц.
     *  Результативная таблица содержит столбцы:
     *  логин сотрудника,
     *  начало и окончание рабочего дня в обычном и unix time представлении, статус
     *
     *  Сортируется по убыванию unixStartTime - т.е.
     *  работники, начавшие ЗАДАННЫЙ рабочий день позднее всех,
     *  отобразятся на первых позициях отчета
     *
     *  {@link worktime.services.EmployerWorkDayService#findReportForSelectedDay(String)}
     */

    SELECTED_DAY_REPORT("SELECT  login, startTime, finishTime, unixStartTime, unixFinishTime, isOnLine "+
            "FROM employerworkday "+
            "JOIN employer ON employer.id = employerworkday.employerId "+
            "WHERE (unixStartTime BETWEEN ? AND ?) "+
            "ORDER BY unixStartTime DESC"),

    /**
     *  Возвращает отчет за указанный период по заданному сотруднику.
     *  Результативная таблица содержит столбцы:
     *  id рабочего дня, id сотрудника,
     *  начало и окончание рабочего дня в обычном и unix time представлении
     *
     *  Сортируется по убыванию unixStartTime - т.е.
     *  на первой позиции отчета отобразится время,
     *  когда работник был на рабочем месте последний раз.
     *
     *  {@link worktime.services.EmployerWorkDayService#findEmployerWorkDayByPeriod(Employer, String, String)}
     */

    EMPLOYER_WORK_DAY_BY_PERIOD("SELECT * " +
            "FROM employerworkday " +
            "WHERE unixStartTime >=? and (unixFinishTime <=? or unixFinishTime is null) and employerId = ? " +
            "ORDER BY unixStartTime DESC"),

    /**
     * Находит все записи по заданному логину.
     * Актуально для таблицы содержащей поле  "login"
     * отмеченных аннотацией {@link worktime.annotation.Table}
     */
    FIND_BY_LOGIN("SELECT * FROM :tableName " +
            "WHERE login= ?"),

    /**
     * Обновляет статус isOnline текущего рабочего дня по его id.
     *
     * {@link worktime.model.EmployerWorkDay}
     * {@link worktime.services.EmployerWorkDayService#updateStatus(EmployerWorkDay)}
     */
    SET_STATUS(("UPDATE employerworkday " +
            "SET isOnline=? " +
            "WHERE id = ?")),

    /**
     * Находит текущий рабочий день для
     * заданного сотрудника по его id
     *
     * {@link worktime.services.EmployerWorkDayService#findCurrentEmployerWorkDay(Employer)}
     */
    CURRENT_EMPLOYER_WORK_DAY("SELECT * " +
            "FROM employerworkday " +
            "WHERE unixStartTime >= ? and employerId =?"),

    /**
     * Обновляет начало текущего рабочего дня по его id
     *
     * {@link worktime.services.EmployerWorkDayService#save(EmployerWorkDay)}
     */
    UPDATE_EMPLOYER_WORK_DAY_BEGIN("UPDATE employerworkday " +
            "SET startTime =?, unixStartTime=? " +
            "WHERE id = ?"),

    /**
     * Обновляет окончание текущего рабочего дня по его id
     *
     * {@link worktime.services.EmployerWorkDayService#save(EmployerWorkDay)}
     */
    UPDATE_EMPLOYER_WORK_DAY_END("UPDATE employerworkday " +
            "SET finishTime=?, unixFinishTime=? " +
            "WHERE id = ?");

    private final String query;

     SqlQuery(final String query){
       this.query = query;
    }

    @Override
     public String toString() {
        return query;
    }
}
