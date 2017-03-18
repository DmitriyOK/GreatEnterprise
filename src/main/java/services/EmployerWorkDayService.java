package services;


import model.Employer;
import model.EmployerWorkDay;
import java.util.List;

/**
 * Created by Dmitriy on 16.03.2017.
 */
public interface EmployerWorkDayService {

    List<EmployerWorkDay> findEmployerWorkDayByPeriod(Employer employer, int startPeriod, int endPeriod);
    List<EmployerWorkDay> findAll();
    EmployerWorkDay save(EmployerWorkDay employerWorkDay);
    EmployerWorkDay findCurrentEmployerWorkDay(Employer employer);
    List<EmployerWorkDay> currentDayReport();
}
