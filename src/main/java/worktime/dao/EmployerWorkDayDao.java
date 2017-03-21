package worktime.dao;


import worktime.model.Employer;
import worktime.model.EmployerWorkDay;
import worktime.model.ReportCurrentDay;

import java.util.List;

/**
 * DAO слой для работы с основной таблицей.
 * Документация к интерфейсам доступна по ссылке: {@link worktime.factory.AbstractEntityFactory}
 */
public interface EmployerWorkDayDao {


    List<EmployerWorkDay> findEmployerWorkDayByPeriod(Employer employer, int startPeriod, int endPeriod);


    List<ReportCurrentDay> findBySelectedPeriod(int selectedUnixTimeDay);


    EmployerWorkDay findCurrentEmployerWorkDay(Employer employer);


    EmployerWorkDay save(EmployerWorkDay employerWorkDay);


    EmployerWorkDay updateStatus(EmployerWorkDay employerWorkDay);


    List<ReportCurrentDay> findCurrentDayReport();
}

