package dao;


import model.Employer;
import model.EmployerWorkDay;
import java.util.List;

/**
 * Created by Dmitriy on 16.03.2017.
 */
public interface EmployerWorkDayDao {

    List<EmployerWorkDay> findEmployerWorkDayByPeriod(Employer employer, int startPeriod, int endPeriod);
    List<EmployerWorkDay> findAll();
    EmployerWorkDay findCurrentEmployerWorkDay(Employer employer);
    EmployerWorkDay save(EmployerWorkDay employerWorkDay);
    List<EmployerWorkDay> currentDayReport();
}

