package services.impl;

import services.EmployerService;
import validator.Validator;
import dao.EmployerWorkDayDao;
import dao.impl.EmployerWorkDayDaoImpl;
import model.Employer;
import model.EmployerWorkDay;
import model.ReportCurrentDay;
import period.Period;
import services.EmployerWorkDayService;
import main.Application;

import java.util.List;

/**
 * CРеализация интерфейса {@link EmployerWorkDayService}
 */
public class EmployerWorkDayServiceImpl implements EmployerWorkDayService{

    private EmployerWorkDayDao employerWorkDayDao = new EmployerWorkDayDaoImpl();

    public List<EmployerWorkDay> findEmployerWorkDayByPeriod(Employer employer, String startPeriod, String endPeriod) {

        if(!Validator.isValidFormat(startPeriod) && !Validator.isValidFormat(endPeriod)){
            Application.printText("Некорректный формат даты. Дата должна соответсвовать формату гггг-мм-дд");
            return null;
        }
        if ( employer == null || !Validator.isValidLogin(employer.getLogin())){
            Application.printText("Укажите корректный логин");
            return null;
        }

        return employerWorkDayDao.findEmployerWorkDayByPeriod(employer, Period.createPeriod(startPeriod), Period.createPeriod(endPeriod));
    }

    public List<ReportCurrentDay> findReportForSelectedDay(String day) {

        int unixTimeDay;
        if(Validator.isValidFormat(day)){
            unixTimeDay = Period.createPeriod(day);
        }
        else {
            Application.printText("Некорректный формат даты. Дата должна соответствовать формату гггг-мм-дд.");
            return null;
        }
        return employerWorkDayDao.findBySelectedPeriod(unixTimeDay);
    }

    public EmployerWorkDay save(EmployerWorkDay employerWorkDay) {
        return employerWorkDayDao.save(employerWorkDay);
    }


    public EmployerWorkDay findCurrentEmployerWorkDay(Employer employer) {
        return employerWorkDayDao.findCurrentEmployerWorkDay(employer);
    }

    public EmployerWorkDay updateStatus(EmployerWorkDay employerWorkDay) {
        return employerWorkDayDao.updateStatus(employerWorkDay);
    }

    public List<ReportCurrentDay> findCurrentDayReport() {
        return employerWorkDayDao.findCurrentDayReport();
    }

}

