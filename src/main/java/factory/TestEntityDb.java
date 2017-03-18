package factory;

import dao.impl.EmployerWorkDayDaoImpl;
import model.Department;
import model.Employer;
import model.EmployerWorkDay;
import period.Period;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by Dmitriy on 17.03.2017.
 */
public class TestEntityDb {
    public static void main(String[] args) throws SQLException, InvocationTargetException, IllegalAccessException, InstantiationException, ParseException, InterruptedException {

//        TestEntityDb.printList(EntityFactory.findAll(Department.class), "findAll");
//        TestEntityDb.printList(EntityFactory.findAll(Employer.class), "findAll");
//        TestEntityDb.printList(EntityFactory.findAll(EmployerWorkDay.class), "findAll");
//
//        TestEntityDb.printList(EntityFactory.findEmployerByLogin("oborisov"), "findEmployerByLogin");
//
//        Employer employer = (Employer) EntityFactory.findEmployerByLogin("oborisov").get(0);
//        TestEntityDb.printList((EntityFactory.findEmployerWorkDayHistoryByPeriod(employer, currentDay() - (86400 * 4), currentDay()+86400)),"findEmployerWorkDayHistoryByPeriod");
//        TestEntityDb.printList(EntityFactory.findCurrentEmployerWorkDay(employer),"findCurrentEmployerWorkDay(employer)");
//
//        TestEntityDb.printList(EntityFactory.findCurrentDayReport(),"findCurrentDayReport");

//        System.out.println(Period.currentDay());
//        System.out.println(Period.createPeriod("2017-03-14"));

        System.out.println("***************Business logic test ******************");

        Employer employer1 = (Employer) EntityFactory.findEmployerByLogin("oborisov").get(0);
        System.out.println(employer1);
        EmployerWorkDay workDay = (EmployerWorkDay) EntityFactory.findCurrentEmployerWorkDay(employer1).get(0);
        Period.createStartTime(workDay);
        System.out.println(EntityFactory.saveEmployerWorkDay(workDay));
        Thread.sleep(5000);
        Period.createFinishTime(workDay);
        System.out.println(EntityFactory.saveEmployerWorkDay(workDay));

    }

    private static void printList(List<Object> objects, String methodName) {

        System.out.println("************* "+methodName+" results *************");
        for (Object obj : objects)
            System.out.println(obj);
    }
}