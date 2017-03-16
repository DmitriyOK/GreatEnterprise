package controller;

import model.EmployerWorkDay;
import services.EmployerWorkDayService;
import services.impl.EmployerWorkDayServiceImpl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Dmitriy on 16.03.2017.
 */
public class AppController {

//    static EmployerWorkDayService dayService = new EmployerWorkDayServiceImpl();
//    static EmployerWorkDay newDay = new EmployerWorkDay();
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
}
