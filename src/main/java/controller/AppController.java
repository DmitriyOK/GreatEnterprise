package controller;

import Validator.Validator;
import model.Employer;
import model.EmployerWorkDay;
import period.Period;
import services.EmployerService;
import services.EmployerWorkDayService;
import services.impl.EmployerServiceImpl;
import services.impl.EmployerWorkDayServiceImpl;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static period.Period.createPeriod;

/**
 * Created by Dmitriy on 16.03.2017.
 */
public class AppController {
    static EmployerWorkDayService employerWorkDayService = new EmployerWorkDayServiceImpl();
    static EmployerService employerService = new EmployerServiceImpl();
//
//    public static void main(String[] args)  {
//        Date date = new Date();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        newDay.setEmployerId(1);
//        newDay.setUnixStartTime((int)(date.getTime()/1000));
//        newDay.setStartTime(format.format(date));
//        System.out.println(newDay);
//        dayService.save(newDay);
//    }

    public static void main(String[] args) throws ParseException {

       // Employer employer = employerService.findByLogin(login);

        //System.out.println(employer);

        String startPeriod="2017-03-14";
        String endPeriod="2017-03-16";



        //System.out.println(dayService.findEmployerWorkDayByPeriod(employer, aStartPeriod, aEndPeriod));

        AppController program = new AppController();
//        Employer employer=program.authorization("oborisov","test");
//        program.startWorkDay();
    }

    private EmployerWorkDay startWorkDay(EmployerWorkDay workDay){
        return employerWorkDayService.save(workDay);
    }

//    public Employer authorization(String login, String password){
//        return Validator.authorization(employerService.findByLogin(login),login,password);
//    }

    public List<EmployerWorkDay> employerReportByPeriod(Employer employer, String startPeriod, String endPeriod ){
        return employerWorkDayService.findEmployerWorkDayByPeriod(employer, createPeriod(startPeriod), createPeriod(endPeriod));
    }

    public List<EmployerWorkDay> currentDayReport(){
        return employerWorkDayService.currentDayReport();
    }



}
