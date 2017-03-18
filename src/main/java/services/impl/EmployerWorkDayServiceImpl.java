package services.impl;

import dao.EmployerWorkDayDao;
import dao.impl.EmployerWorkDayDaoImpl;
import model.Employer;
import model.EmployerWorkDay;
import services.EmployerWorkDayService;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Dmitriy on 16.03.2017.
 */
public class EmployerWorkDayServiceImpl implements EmployerWorkDayService{

    EmployerWorkDayDao employerWorkDayDao = new EmployerWorkDayDaoImpl();

    public List<EmployerWorkDay> findEmployerWorkDayByPeriod(Employer employer, int startPeriod, int endPeriod) {
        return employerWorkDayDao.findEmployerWorkDayByPeriod(employer, startPeriod, endPeriod);
    }

    public List<EmployerWorkDay> findAll() {
        return employerWorkDayDao.findAll();
    }


    public EmployerWorkDay save(EmployerWorkDay employerWorkDay) {
        return employerWorkDayDao.save(employerWorkDay);
    }

    @Override
    public EmployerWorkDay findCurrentEmployerWorkDay(Employer employer) {
        return employerWorkDayDao.findCurrentEmployerWorkDay(employer);
    }

    @Override
    public List<EmployerWorkDay> currentDayReport() {
        return employerWorkDayDao.currentDayReport();
    }

}

