package controller;

import model.Employer;
import model.EmployerWorkDay;
import services.EmployerService;
import services.EmployerWorkDayService;
import services.impl.EmployerServiceImpl;
import services.impl.EmployerWorkDayServiceImpl;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Dmitriy on 16.03.2017.
 */
public class AppController {
    static EmployerWorkDayService dayService = new EmployerWorkDayServiceImpl();
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

        Employer employer = employerService.findByLogin("apetrova");
        System.out.println(employer);

        String startPeriod="2017-03-14";
        String endPeriod="2017-03-16";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = sdf.parse(startPeriod);
        Date endDate = sdf.parse(endPeriod);
        int aStartPeriod = (int)(startDate.getTime()/1000);
        int aEndPeriod = (int)(endDate.getTime()/1000);

        System.out.println(dayService.findEmployerWorkDayByPeriod(employer, aStartPeriod, aEndPeriod));

    }

}
