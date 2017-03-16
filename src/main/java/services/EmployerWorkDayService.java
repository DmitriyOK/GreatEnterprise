package services;

import com.sun.istack.internal.Nullable;
import model.Employer;
import model.EmployerWorkDay;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by Dmitriy on 16.03.2017.
 */
public interface EmployerWorkDayService {

    List<EmployerWorkDay> findEmployerWorkDayByPeriod(@Nullable Employer employer, int startPeriod, int endPeriod);
    List<EmployerWorkDay> findAll();
    EmployerWorkDay save(EmployerWorkDay employerWorkDay);
}
