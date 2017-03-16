package dao;

import com.sun.istack.internal.Nullable;
import model.Employer;
import model.EmployerWorkDay;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Dmitriy on 16.03.2017.
 */
public interface EmployerWorkDayDao {

    List<EmployerWorkDay> findEmployerWorkDayByPeriod(Employer employer, int startPeriod, int endPeriod);
    List<EmployerWorkDay> findAll();
    EmployerWorkDay findByLogin(String login);
    EmployerWorkDay save(EmployerWorkDay employerWorkDay);


}

