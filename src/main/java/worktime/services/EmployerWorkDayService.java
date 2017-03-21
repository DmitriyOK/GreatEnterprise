package worktime.services;


import worktime.model.Employer;
import worktime.model.EmployerWorkDay;
import worktime.model.ReportCurrentDay;

import java.util.List;

/**
 * Created by Dmitriy on 16.03.2017.
 */
public interface EmployerWorkDayService {

    /**
     * Возвращает отчет занятости заданного
     * пользователя за указанный период включительно.
     *
     * {@link worktime.sqlquery.SqlQuery#EMPLOYER_WORK_DAY_BY_PERIOD}
     * @param employer текущий сотрудник
     * @param startPeriod  начало отчетного периода
     * @param endPeriod конец отчетного периода
     * @return {@link List} история занятости сотрудника за период.
     */
    List<EmployerWorkDay> findEmployerWorkDayByPeriod(Employer employer, String startPeriod, String endPeriod);

    /**
     * Возвращает историю занятости всех сотрудников
     * за заданный день.
     *
     * {@link worktime.sqlquery.SqlQuery#SELECTED_DAY_REPORT}
     * @param day заданный день
     * @return {@link List} история занятости за заданный день.
     */
    List<ReportCurrentDay> findReportForSelectedDay(String day);

    /**
     * Возвращает отчет за текущий рабочий день по всем сотрудникам.
     *
     * {@link worktime.sqlquery.SqlQuery#CURRENT_DAY_REPORT}
     * @return {@link ReportCurrentDay} - отчет за текущий день
     */
    List<ReportCurrentDay> findCurrentDayReport();

    /**
     * Возвращает текущий рабочий день для заданного сотрудника.
     *
     * {@link worktime.sqlquery.SqlQuery#CURRENT_EMPLOYER_WORK_DAY}
     * @param employer - теущий сотрудник
     * @return {@link EmployerWorkDay} текущий рабочий день для заданного сотрудника.
     */
    EmployerWorkDay findCurrentEmployerWorkDay(Employer employer);

    /**
     * Обновляет статус Личного кабинета сотрудника isOnline
     * в течение текущего рабочего дня.
     *
     * {@link worktime.sqlquery.SqlQuery#SET_STATUS}
     * @param employerWorkDay текущий рабочий день с обновленным статусом.
     * @return {@link EmployerWorkDay} текущий рабочий день для заданного сотрудника.
     */
    EmployerWorkDay updateStatus(EmployerWorkDay employerWorkDay);

    /**
     * Сохраняет информацию по текущему рабочему дню в базу данных
     * и возвращает обновленный объект
     *
     * {@link worktime.sqlquery.SqlQuery#UPDATE_EMPLOYER_WORK_DAY_BEGIN}
     * {@link worktime.sqlquery.SqlQuery#UPDATE_EMPLOYER_WORK_DAY_END}
     * @param employerWorkDay теущий рабочий день
     * @return {@link EmployerWorkDay} текущий день.
     */
    EmployerWorkDay save(EmployerWorkDay employerWorkDay);

}
