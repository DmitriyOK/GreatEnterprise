package period;

import model.EmployerWorkDay;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Класс представляет работу с периодами даты.
 * Возвращает unixTime в секундах.
 */
public abstract class Period {
    private final static SimpleDateFormat WORK_DAY_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final static SimpleDateFormat PERIOD_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * Конвертирует указанную {@link String} дату в unixTime на начало дня.
     * Дата должна соответствовать формату <p>"yyyy-MM-dd"</p>
     *
     * @param period дата, которую нужно конвертировать.
     * @return int unixTime или -1, если дата не соответсвует формату.
     */
    public static int createPeriod(String period){
        Date startDate;
        try {
            startDate = PERIOD_DATE_FORMAT.parse(period);
        } catch (ParseException e) {
            return -1;
        }
        return (int)(startDate.getTime()/1000);
    }

    /**
     * Возвращает начало текущего дня в секундах.
     *
     * @return int unixTime
     */
    public static int currentDay() {
        long temp;
        java.util.Date date = new java.util.Date();
        temp = date.getTime()/1000;
        temp = temp/86400;
        return (int) temp*86400;
    }

    /**
     * Метод исопльзуется для изменения начала рабочего времени работника
     * Позволяет изменить время, установленное по умолчанию, если параметр <p>isOnline</p>
     * экземпляра класса {@link EmployerWorkDay} равен false
     *
     * @param workDay - рабочий день сотрудника
     * @return true если дата изменена или false если дата была ранее указана
     *         и изменению не подлежит.
     */
    public static boolean createStartTime(EmployerWorkDay workDay ){
        if(!workDay.isOnline()) {
            Date date = new Date();
            workDay.setUnixStartTime((int) (date.getTime() / 1000));
            workDay.setStartTime(WORK_DAY_DATE_FORMAT.format(date));
            return true;
        }
        return false;
    }

    /**
     * Метод используется для изменения конца рабочего времени работника
     *
     * @param workDay - рабочий день сотрудника
     * @return true если дата изменена или false если время окончания рабочего
     * было ранее указано и изменению не подлежит.
     */
    public static boolean createFinishTime(EmployerWorkDay workDay ){
        if(workDay.getFinishTime() != null || workDay.getUnixFinishTime() != 0){
            return false;
        }
        Date date = new Date();
        workDay.setUnixFinishTime((int)(date.getTime()/1000));
        workDay.setFinishTime(WORK_DAY_DATE_FORMAT.format(date));

        return true;
    }

}
