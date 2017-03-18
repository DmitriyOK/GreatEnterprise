package services;


import model.Employer;
import model.EmployerWorkDay;
import model.ReportCurrentDay;

import java.util.List;

/**
 * Created by Dmitriy on 16.03.2017.
 */
public interface EmployerWorkDayService {

    List<EmployerWorkDay> findEmployerWorkDayByPeriod(Employer employer, String startPeriod, String endPeriod);
    List<ReportCurrentDay> findReportForSelectedDay(String day);
    EmployerWorkDay save(EmployerWorkDay employerWorkDay);
    EmployerWorkDay findCurrentEmployerWorkDay(Employer employer);
    EmployerWorkDay updateStatus(EmployerWorkDay employerWorkDay);
    List<ReportCurrentDay> findCurrentDayReport();

}
